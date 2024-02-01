package com.example.practise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.practise.dao.MemberRepo;
import com.example.practise.entity.Members;
import com.example.practise.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class deleteMemberEntity {

	@Autowired
	MemberRepo memberRepo;

	@GetMapping("/deleteMemberEntity/{id}")
	public String deleteMemberEntity(@PathVariable String id,Model model,HttpSession session) {
		memberRepo.deleteById(Integer.parseInt(id));
		User user = (User) session.getAttribute("userEntity");
		// Check if user is not null before accessing properties
		if (user != null) {
			int user_id = user.getId();
			List<Members> members = memberRepo.getAllMemberEntityByUserId(user_id);
			model.addAttribute("members", members);

			for (Members member : members) {
				System.out.println("Members in the members table" + member);
			}

			if (members.isEmpty()) {
				System.out.println("Empty Member List");
			}
		} else {
			// Handle the case when user is null (e.g., redirect to login page)
			return "redirect:/showloginpage";
		}
		return "displayAllMembers";
	}

}
