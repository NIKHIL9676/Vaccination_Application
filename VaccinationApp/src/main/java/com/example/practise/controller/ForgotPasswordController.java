package com.example.practise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotPasswordController {
	@GetMapping("/forgottenPassword")
	public String getForgetPage() {
		return "forgotPassword";
	}
}
