package universityconnect.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import universityconnect.domain.ResourceCategory;
import universityconnect.dto.ResourceCategoryDTO;
import universityconnect.dto.ResourceDTO;
import universityconnect.service.ResourceCategoryService;
import universityconnect.service.ResourceService;
import universityconnect.service.StorageService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/resources")
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    private final ResourceService resourceService;
    private final StorageService storageService;
    private final ResourceCategoryService resourceCategoryService;

    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(@RequestParam("file")MultipartFile file,@RequestParam("resourceCategoryId")Long categoryId){
        ResourceCategoryDTO resourceCategory = resourceCategoryService.getResourceCategoryById(categoryId);
        storageService.store(resourceCategory.getName(),file,resourceCategory.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources(@RequestParam(value= "categoryId",required = false) Long categoryId) throws IOException {
        if(categoryId != null ){
            List<ResourceDTO> resources = resourceService.getAllResources().stream()
                    .filter(resource -> Objects.equals(resource.getResourceCategoryId(), categoryId))
                    .toList();
            return ResponseEntity.ok(resources);
        }else{
            List<ResourceDTO> resources = resourceService.getAllResources();
            return ResponseEntity.ok(resources);
        }

    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> getResourceFile(@PathVariable long id){
        ResourceDTO resource = resourceService.getResourceById(id);
        ResourceCategoryDTO category = resourceCategoryService.getResourceCategoryById(resource.getResourceCategoryId());

        Resource file = storageService.loadAsResource(category.getName()+"/"+resource.getFileName());

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);

    }


    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable long id){
        ResourceDTO resource = resourceService.getResourceById(id);
        return ResponseEntity.ok(resource);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ResourceDTO> updateResource(@PathVariable long id, @RequestBody ResourceDTO resourceDTO){
//        ResourceDTO resource = resourceService.updateResource(id,resourceDTO);
//        return ResponseEntity.ok(resource);
//    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable long id){
        resourceService.deleteResource(id);
    }
}
