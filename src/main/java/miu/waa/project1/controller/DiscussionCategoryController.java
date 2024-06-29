package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;

import miu.waa.project1.model.DiscussionCategory;
import miu.waa.project1.service.DiscussionCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussion/category")
@RequiredArgsConstructor
public class DiscussionCategoryController {
	private final DiscussionCategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<DiscussionCategory>> getAll() {
		return ResponseEntity.ok().body(categoryService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DiscussionCategory> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id).orElse(null));
	}

	@PostMapping
	public ResponseEntity<DiscussionCategory> createOne(@RequestBody DiscussionCategory category) {
		DiscussionCategory item = categoryService.createOne(category);
		return ResponseEntity.ok().body(item);
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateOne(@RequestBody DiscussionCategory category, @PathVariable Long id) {
		DiscussionCategory item = categoryService.updateOne(id, category).orElse(null);
		if (item == null) {
			return ResponseEntity.badRequest().body("User not found!");
		}
		return ResponseEntity.ok().body(item);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable Long id) {
		DiscussionCategory item = categoryService.deleteOne(id).orElse(null);
		if (item == null) {
			return ResponseEntity.badRequest().body("User not found!");
		}
		return ResponseEntity.ok().body(item);
	}
}
