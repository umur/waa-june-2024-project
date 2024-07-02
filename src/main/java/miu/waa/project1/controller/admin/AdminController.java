package miu.waa.project1.controller.admin;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.common.AccountStatus;
import miu.waa.project1.model.User;
import miu.waa.project1.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
	private final UserService userService;

	@GetMapping("/accounts/{id}")
	public ResponseEntity<?> updateStatusAccount(
			@PathVariable Long id,
			@RequestParam(name = "status") AccountStatus status
	) {
		try {
			User item = userService.updateStatus(id, status);
			return ResponseEntity.ok().body(item);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/accounts")
	public ResponseEntity<?> getAllUserAccount() {
		try {
			List<User> item = userService.getAll();
			return ResponseEntity.ok().body(item);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
