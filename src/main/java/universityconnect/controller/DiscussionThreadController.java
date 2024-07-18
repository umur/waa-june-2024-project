package universityconnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.DiscussionThreadDTO;
import universityconnect.service.DiscussionThreadService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/discussions/threads")
@RequiredArgsConstructor
@CrossOrigin
public class DiscussionThreadController {
    private final DiscussionThreadService threadService;
    @PostMapping
    public ResponseEntity<DiscussionThreadDTO> createDiscussionThread(@RequestBody DiscussionThreadDTO discussionThreadDTO) {
        DiscussionThreadDTO createdDiscussionDTO = threadService.createDiscussionThread(discussionThreadDTO);
        return new ResponseEntity<>(createdDiscussionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscussionThreadDTO> getDiscussionThreadById(@PathVariable Long id) {
        DiscussionThreadDTO discussionThreadDTO = threadService.getDiscussionThreadById(id);
        return new ResponseEntity<>(discussionThreadDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DiscussionThreadDTO>> getAllDiscussionThread() {
        List<DiscussionThreadDTO> discussionThreadDTOS = threadService.getAllDiscussionThread();
        return ResponseEntity.ok(discussionThreadDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscussionThreadDTO> updateDiscussionThreadById(@PathVariable Long id, @RequestBody DiscussionThreadDTO discussionThreadDTO) {
        DiscussionThreadDTO updatedDiscussionThread = threadService.updateDiscussionThread(id,discussionThreadDTO);
        return ResponseEntity.ok(updatedDiscussionThread);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussionThreadById(@PathVariable Long id) {
        threadService.deleteDiscussionThread(id);
        return ResponseEntity.noContent().build();
    }
}
