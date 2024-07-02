package universityconnect.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.ResourceDTO;
import universityconnect.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO){
        ResourceDTO resource = resourceService.createResource(resourceDTO);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources(){
        List<ResourceDTO> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable long id){
        ResourceDTO resource = resourceService.getResourceById(id);
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceDTO> updateResource(@PathVariable long id, @RequestBody ResourceDTO resourceDTO){
        ResourceDTO resource = resourceService.updateResource(id,resourceDTO);
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable long id){
        resourceService.deleteResource(id);
    }
}
