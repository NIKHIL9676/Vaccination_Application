package com.example.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practise.entity.User;
import com.example.practise.service.EmailSender;
import com.example.practise.service.Message;
import com.example.practise.service.UserService;

@Controller
public class UserRegistrationController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailSender emailSender;

	@PostMapping("/do_register")
	public String registerUserEntity(@ModelAttribute("user") User user,
			@RequestParam("confirm_password") String confirmPassword, Model model) {

		if (!user.getPassword().equals(confirmPassword)) {
			// Passwords don't match, handle accordingly
			model.addAttribute("message", new Message("error", "Passwords do not match."));
			return "signup";
		}

		User user1 = userService.getUserEntityByEmail(user.getEmail());

		if (user1 == null) {
			user.setEnabled(true);
			user.setRole("USER");
			user.setPassword("{noop}" + user.getPassword());
			boolean isSaved = userService.saveUser(user);
			String newPassword = "";
			for (int i = 6; i < user.getPassword().length(); i++) {
				newPassword += user.getPassword().charAt(i);
			}
			if (isSaved) {
				String subject = "Registered Successfully...!";
				String body = "Hi " + user.getName()
						+ "\n\n You have been Successfully registered\n\n Your Login Credentials as follows: \n\t\t username/email Id - "
						+ user.getEmail() + "\n\t\tPassword - " + newPassword;
				model.addAttribute("message", new Message("saved", "Registered Successfully....!"));
				emailSender.sendEmail(user.getEmail(), subject, body);

				return "login";
			}
		}
		model.addAttribute("message", new Message("error", "Email id with "+user.getEmail()+" has been already registered...!"));

		return "signup";
	}

}
