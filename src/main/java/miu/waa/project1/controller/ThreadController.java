package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.CreateOrUpdateThreadRequest;
import miu.waa.project1.model.Thread;
import miu.waa.project1.service.ThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/thread")
@RequiredArgsConstructor
public class ThreadController {
	private final ThreadService reviewService;

	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody CreateOrUpdateThreadRequest req) {
		try {
			Thread resp = reviewService.createOne(req.getThread(), req.getDiscussionId());
			return ResponseEntity.ok().body(resp);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody CreateOrUpdateThreadRequest req, @PathVariable Long id) {
		Optional<Thread> resp = reviewService.updateOne(id, req.getThread());
		if (resp.isEmpty()) {
			return ResponseEntity.badRequest().body("Review ID not found!");
		}
		return ResponseEntity.ok().body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Thread> deletedItem = reviewService.deleteOne(id);
		if (deletedItem.isEmpty()) {
			return ResponseEntity.badRequest().body("Review ID not found!");
		}
		return ResponseEntity.ok().body(deletedItem);
	}
}
