package com.example.practise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.practise.entity.Members;
import com.example.practise.service.MemberService;
@Controller
public class EditMemberEntityDetails {
	@Autowired
	MemberService memberService;
	
	@GetMapping("/editMemberEntity/{id}")
    public String editMemberForm(@PathVariable int id, Model model) {
        Members member=memberService.getMemberById(id);
        System.out.println("Edit member Entity --->"+member);
        model.addAttribute("member", member);
        return "editMemberForm";
    }

}
