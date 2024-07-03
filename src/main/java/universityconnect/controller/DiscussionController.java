package universityconnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.domain.Discussion;
import universityconnect.domain.DiscussionSearchCriteria;
import universityconnect.dto.DiscussionDTO;
import universityconnect.dto.DiscussionSearchResponseDTO;
import universityconnect.service.DiscussionService;

import java.util.List;
@RestController
@RequestMapping("/discussions")
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionService discussionService;

    @PostMapping
    public ResponseEntity<DiscussionDTO> createDiscussion(@RequestBody DiscussionDTO discussionDTO) {
        DiscussionDTO createdDiscussion = discussionService.createDiscussion(discussionDTO);
        return new ResponseEntity<>(createdDiscussion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscussionDTO> getDiscussionById(@PathVariable Long id) {
        DiscussionDTO discussionDTO = discussionService.getDiscussionById(id);
        return new ResponseEntity<>(discussionDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DiscussionDTO>> getAllDiscussion() {
        List<DiscussionDTO> discussionDTOS = discussionService.getAllDiscussion();
        return ResponseEntity.ok(discussionDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscussionDTO> updateDiscussionById(@PathVariable Long id, @RequestBody DiscussionDTO discussionDTO) {
        DiscussionDTO updatedDiscussion = discussionService.updateDiscussion(id,discussionDTO);
        return ResponseEntity.ok(updatedDiscussion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussionById(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public DiscussionSearchResponseDTO searchDiscussions(@RequestBody DiscussionSearchCriteria criteria, Pageable pageable){
        return discussionService.searchDiscussions(criteria, pageable);
    }
}
