package universityconnect.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.DiscussionCategoryDTO;
import universityconnect.dto.UserDTO;
import universityconnect.service.DiscussionCategoryService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/discussion-categories")
@RequiredArgsConstructor
@CrossOrigin
public class DiscussionCategoryController {
    private final DiscussionCategoryService discussionCategoryService;

    @PostMapping
    public ResponseEntity<DiscussionCategoryDTO> createDiscussionCategory(@RequestBody DiscussionCategoryDTO discussionCategoryDTO) {
        DiscussionCategoryDTO createdCategory = discussionCategoryService.createDiscussionCategory(discussionCategoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscussionCategoryDTO> getDiscussionCategoryById(@PathVariable Long id) {
        DiscussionCategoryDTO categoryDTO = discussionCategoryService.getDiscussionCategoryById(id);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DiscussionCategoryDTO>> getAllDiscussionCategory() {
        List<DiscussionCategoryDTO> categoryDTOS = discussionCategoryService.getAllDiscussionCategory();
        return ResponseEntity.ok(categoryDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscussionCategoryDTO> updateDiscussionCategoryById(@PathVariable Long id, @RequestBody DiscussionCategoryDTO discussionCategoryDTO) {
        DiscussionCategoryDTO updatedCategory = discussionCategoryService.updateDiscussionCategory(id,discussionCategoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussionCategoryById(@PathVariable Long id) {
        discussionCategoryService.deleteDiscussionCategory(id);
        return ResponseEntity.noContent().build();
    }

}
