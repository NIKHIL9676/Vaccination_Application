package com.example.practise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.practise.entity.Members;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class DemoController {
	@GetMapping("/")
	public String home(Model model, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");

		model.addAttribute(getMethodName(), model);
		return "home";
	}
	@GetMapping("/showloginpage")
	public String getMethodName() {
		return "login";
	}
	@GetMapping("/signup")
	public String getSignUp() {
		return "signup";
	}
	
	@GetMapping("/admin_meeting")
	public String getAdminMeeting() {
		return "admin_meeting";
	}
	
	
	@GetMapping("/addmember")
	public String addmember() {
		return "addmember";
	}
	
	@GetMapping("/employee_meeting")
	public String getEmployeeMeeting() {
		return "employee_meeting";
	}
	@GetMapping("/maneger_meeting")
	public String getManagerMeeting() {
		return "maneger_meeting";
	}
	
	@GetMapping("/access_denied")
	public String getErrorPage()
	{
		return "errorPage";
	}
	
	@GetMapping("/landing_page")
	public String getLandingPage() {
		return "landing_page";
	}
	
	
}
