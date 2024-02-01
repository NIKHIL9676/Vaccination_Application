package com.example.practise.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.practise.dao.MemberRepo;
import com.example.practise.entity.Members;

import jakarta.transaction.Transactional;

@Component
public class MemberService {

	@Autowired
	MemberRepo memberRepo;

	public Members getMemberById(int id) {
		return memberRepo.findById(id);
	}


    @Transactional
    public boolean updateMemberEntity(int id, String name, String identityType, 
                                      String identityNumber, String vaccineName, int noOfDoses) {
        int rowsUpdated = memberRepo.updateMemberEntity(id, name, identityType,
                                                        identityNumber, vaccineName, noOfDoses);
        System.out.println(rowsUpdated+"--> updated rows");

        return rowsUpdated > 0;
    }
    public List<Members> getAllByUserId(int userId){
    	return memberRepo.findAllByUserId(userId);
    }

}
