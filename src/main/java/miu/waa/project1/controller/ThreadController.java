package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.CreateOrUpdateThreadRequest;
import miu.waa.project1.model.Thread;
import miu.waa.project1.service.impl.ThreadServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/thread")
@RequiredArgsConstructor
public class ThreadController {
	private final ThreadServiceImpl threadService;

	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody CreateOrUpdateThreadRequest req) {
		try {
			Thread resp = threadService.createOne(req.getThread(), req.getDiscussionId());
			return ResponseEntity.ok().body(resp);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Thread> getById(@PathVariable Long id) {
		return ResponseEntity.ok().body(threadService.findById(id).orElse(null));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Thread req, @PathVariable Long id) {
		Optional<Thread> resp = threadService.updateOne(id, req);
		if (resp.isEmpty()) {
			return ResponseEntity.badRequest().body("Thread ID not found!");
		}
		return ResponseEntity.ok().body(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Thread> deletedItem = threadService.deleteOne(id);
		if (deletedItem.isEmpty()) {
			return ResponseEntity.badRequest().body("Review ID not found!");
		}
		return ResponseEntity.ok().body(deletedItem);
	}
}
