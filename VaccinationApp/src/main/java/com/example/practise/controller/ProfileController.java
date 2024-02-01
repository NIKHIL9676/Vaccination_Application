package com.example.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practise.entity.User;
import com.example.practise.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {
	@Autowired
	UserService userService;
    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("userEntity");
        model.addAttribute("user", user);
        return "viewProfile";
    }

    @GetMapping("/editUserDetails/{id}")
    public String editUserDetails(@ModelAttribute("user") User user,Model model) {
        System.out.println("User Details: " + user);
        User user1=userService.getUserById(user.getId());
        System.out.println(user1);
        String newPassword="";
        String password=user1.getPassword();
        for(int i=6;i<password.length();i++) {
        	newPassword+=password.charAt(i);
        }
        password.replace("{noop}", "");
        user1.setPassword(newPassword);
        System.out.println(newPassword);
        model.addAttribute("user",user1);
        return "editUserEntity";
    }
    
    @PostMapping("/updateUserEntity")
    public String updateUserEntity(@ModelAttribute("user")User user,Model model,@RequestParam("confirm_password")String CPassword) {
    	
    	System.out.println(user);
    	if(!(user.getPassword().equals(CPassword))) {
    		model.addAttribute("response", "Password Doesn't Matched....!");
    		return "editUserEntity";
    	}
    	boolean isUpdated=userService.updateUserEntity(user.getId(), user.getName(), user.getEmail(), CPassword);
    	if(isUpdated) {
    		
    		model.addAttribute("response", "Updated Successfully....!");
    		model.addAttribute("user", user);
    		return "viewProfile";
    		
    	}
    	return null;
    }

}
