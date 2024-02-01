package com.example.practise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.practise.dao.MemberRepo;
import com.example.practise.entity.Members;
import com.example.practise.entity.User;
import com.example.practise.service.MemberService;
import com.example.practise.service.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberRegistrationController {

	@Autowired
	MemberRepo memberRepo;
	@Autowired
	MemberService memberService;

	@PostMapping("/registermember")
	public String registerMemberEntity(@ModelAttribute("member") Members member, HttpSession session, Model model) {
		User user = (User) session.getAttribute("userEntity");
		member.setUser(user);
		System.out.println(member);
		
		List<Members> members=memberService.getAllByUserId(user.getId());
		if(members.size()<5) {

			memberRepo.save(member);

			member = null;
			model.addAttribute("message", new Message("saved", "You have been added member successfully"));


			return "addmember";

		}
		model.addAttribute("message", new Message("error","You Cannot add more than 5 members"));
		return "addmember";
	}

	@GetMapping("/view_members")
	public String viewAllMemberEntity(HttpSession session, Model model) {
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
