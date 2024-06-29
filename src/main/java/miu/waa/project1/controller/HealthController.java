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
@RequestMapping("")
@RequiredArgsConstructor
public class HealthController {
	private final ThreadService reviewService;

	@GetMapping
	public String check() {
		return "Ok";
	}

}
