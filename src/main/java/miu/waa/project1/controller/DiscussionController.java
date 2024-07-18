package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.CreateOrUpdateDiscussionRequest;
import miu.waa.project1.model.Discussion;
import miu.waa.project1.service.impl.DiscussionServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/discussion")
@RequiredArgsConstructor
public class DiscussionController {
	private final DiscussionServiceImpl discussionService;

	@GetMapping("")
	public ResponseEntity<List<Discussion>> getAll(
			@RequestParam Optional<Integer> pageNumber,
			@RequestParam Optional<String> sortBy,
			@RequestParam Optional<Integer> total,
			@RequestParam Optional<Integer> keyword
	) {
		Pageable pageRequest = PageRequest.of(
				pageNumber.orElse(0),
				total.orElse(10),
				Sort.Direction.ASC,
				sortBy.orElse("id")
		);
		return ResponseEntity.ok().body(discussionService.getAll(pageRequest, keyword == null ? "" : String.valueOf(keyword)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Discussion> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(discussionService.findById(id).orElse(null));
	}

	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody CreateOrUpdateDiscussionRequest req) {
		try {
			Discussion item = discussionService.createOne(req.getDiscussion(), req.getCategoryId());
			return ResponseEntity.ok().body(item);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateOne(@RequestBody Discussion user, @PathVariable Long id) {
		Discussion item = discussionService.updateOne(id, user).orElse(null);
		if (item == null) {
			return ResponseEntity.badRequest().body("User not found!");
		}
		return ResponseEntity.ok().body(item);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOne(@PathVariable Long id) {
		Discussion item = discussionService.deleteOne(id).orElse(null);
		if (item == null) {
			return ResponseEntity.badRequest().body("User not found!");
		}
		return ResponseEntity.ok().body(item);
	}
}
