package universityconnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.ResourceCategoryDTO;
import universityconnect.service.ResourceCategoryService;

import java.util.List;

@RestController
@RequestMapping("/resource-categories")
@RequiredArgsConstructor
public class ResourceCategoryController {

    private final ResourceCategoryService resourceCategoryService;

    @PostMapping
    public ResponseEntity<ResourceCategoryDTO> createResourceCategory(@RequestBody ResourceCategoryDTO resourceCategoryDTO){
        ResourceCategoryDTO resourceCategory = resourceCategoryService.createResourceCategory(resourceCategoryDTO);
        return new ResponseEntity<>(resourceCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResourceCategoryDTO>> getAllResourceCategories(){
        List<ResourceCategoryDTO> resourceCategories = resourceCategoryService.getAllResourceCategories();
        return ResponseEntity.ok(resourceCategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceCategoryDTO> getResourceCategoryById(@PathVariable long id){
        ResourceCategoryDTO resourceCategory = resourceCategoryService.getResourceCategoryById(id);
        return ResponseEntity.ok(resourceCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceCategoryDTO> updateResourceCategory(@PathVariable long id, @RequestBody ResourceCategoryDTO resourceCategoryDTO){
        ResourceCategoryDTO resourceCategory = resourceCategoryService.updateResourceCategory(id, resourceCategoryDTO);
        return ResponseEntity.ok(resourceCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteResourceCategory(@PathVariable long id){
        resourceCategoryService.deleteResourceCategory(id);
    }
}
