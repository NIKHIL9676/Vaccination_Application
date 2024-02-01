package com.example.practise.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practise.entity.User;
import com.example.practise.service.EmailSender;
import com.example.practise.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OtpController {
	@Autowired
	UserService userService;
	@Autowired
	EmailSender emailSender;

	@PostMapping("/sendOTP")
	public String sendOTP(@RequestParam("email") String email, Model model,HttpSession session) {
		User user = userService.getUserEntityByEmail(email);
		if (user != null) {
			Random random = new Random();
			int sixDigitRandomNumber = 100000 + random.nextInt(900000);
			String subject = "Vaccination Application";
			String body = "Hi\n\nOTP for Email verification\n\nPlease use the following One Time Password to verify your Email and reset password \n\n"
					+ "OTP: " + sixDigitRandomNumber;
			emailSender.sendEmail(email, subject, body);
			session.setAttribute("userEntity", user);
			session.setAttribute("otp", ""+sixDigitRandomNumber); // Set the otp value in the session
			return "OtpValidation";

		}
		model.addAttribute("response", "Email id " + email + " doesn't exist");
		return "forgotPassword";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam("fotp") String fotp,
			@RequestParam("password") String password, @RequestParam("confirm_password") String CPassword, Model model,
			HttpServletRequest request, // Change from HttpRequest to HttpServletRequest
			HttpSession session) { // Add HttpSession parameter
		System.out.println("FOTP->" + fotp + "\n" + "Password->" + password + "\nConfirm Password->"
				+ CPassword);
		String otp=(String) session.getAttribute("otp");
		if (!(otp.equals(fotp))) {
			model.addAttribute("response", "Invalid OTP. Try Again...!");
			System.out.println("OTP in the OTP checker: " + session.getAttribute("otp"));
			return "OtpValidation";
		} else if (!(password.equals(CPassword))) {
			model.addAttribute("response", "Password doesn't match. Try Again....!");
			System.out.println("OTP in the Password checker: " + session.getAttribute("otp"));
			return "OtpValidation";
		}
		User user = (User) session.getAttribute("userEntity");
		user.setPassword("{noop}"+password);
		userService.saveUser(user);
		model.addAttribute("response", "Password has been updated successfully. Login here");
		return "login";
	}

}
