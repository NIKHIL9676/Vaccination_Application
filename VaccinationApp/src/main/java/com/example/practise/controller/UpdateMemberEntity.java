package com.example.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.practise.entity.Members;
import com.example.practise.service.MemberService;

@Controller
public class UpdateMemberEntity {
	@Autowired
	MemberService memberService;
	@PostMapping("/updateMemberEntity")
	public String updateMemberEntity(@ModelAttribute("member")Members member,Model model) {
		boolean isUpdated=memberService.updateMemberEntity(member.getId(),member.getName(), member.getIdentity_type(), member.getIdentity_number(), member.getVaccine_name(), member.getNo_of_doses());
		System.out.println("Member Entity saved--> "+isUpdated);
		System.out.println(member);
		model.addAttribute("response", "Member Details updated successfully..!");
		model.addAttribute("member", member);
		return "editMemberForm";
	}
}
