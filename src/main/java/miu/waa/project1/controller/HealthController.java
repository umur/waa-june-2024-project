package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.service.impl.ThreadServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HealthController {
	private final ThreadServiceImpl reviewService;

	@GetMapping
	public String check() {
		return "Ok";
	}

}
