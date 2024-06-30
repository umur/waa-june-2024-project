package edu.miu.cs545.project.controller;

import edu.miu.cs545.project.dto.ThreadPostDto;
import edu.miu.cs545.project.service.ThreadPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/threadposts")
public class ThreadPostController {

    @Autowired
    private ThreadPostService  threadPostService;

    @GetMapping
    public ResponseEntity<List<ThreadPostDto>> getAllPostThreads() {
        return ResponseEntity.ok(threadPostService.getAllThreadPost());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostThreadById(@PathVariable Long id) {
        return ResponseEntity.ok(threadPostService.getThreadPostById(id));
    }

    @PostMapping
    public ResponseEntity<?> createPostThread(@RequestBody ThreadPostDto threadPostDto) {
        return ResponseEntity.ok(threadPostService.save(threadPostDto));
    }

    @PutMapping
    public ResponseEntity<ThreadPostDto> updatePostThread(@RequestBody ThreadPostDto threadPostDto) {
        return ResponseEntity.ok(threadPostService.updateThreadPost(threadPostDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostThread(@PathVariable Long id) {
        threadPostService.deleteThreadPost(id);
        return ResponseEntity.noContent().build();
    }
}
