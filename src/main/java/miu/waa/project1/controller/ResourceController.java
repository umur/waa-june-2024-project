package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Resource;
import miu.waa.project1.service.ResourceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/resources")
public class ResourceController {
    private final ResourceService resourceService;

    @GetMapping("")
    public List<Resource> getAllResources(@RequestParam Optional<Integer> pageNumber,
                                          @RequestParam Optional<String> sortBy,
                                          @RequestParam Optional<Integer> total) {
        Pageable pageRequest = PageRequest.of(
                pageNumber.orElse(0),
                total.orElse(10),
                Sort.Direction.ASC,
                sortBy.orElse("id")
        );
        return (List<Resource>) ResponseEntity.ok(resourceService.getAllResources(pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return ResponseEntity.ok(resourceService.getResourceById(id));
    }

    @PostMapping("")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourceService.createResource(resource));
    }

    @PostMapping("/upload")
    public Resource uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam String desc) throws IOException {
        return ResponseEntity.ok(resourceService.upload(multipartFile, desc)).getBody();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(@PathVariable Long id, @RequestBody Resource resource) {
        return ResponseEntity.ok(resourceService.updateResource(id, resource));
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }

    @GetMapping("/user/{userId}")
    public List<Resource> getResourcesByUserId(@PathVariable Long userId) {
        return resourceService.getResourcesByUserId(userId);
    }
}
