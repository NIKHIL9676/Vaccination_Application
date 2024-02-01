package com.example.practise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.practise.entity.Members;

public interface MemberRepo extends JpaRepository<Members, Integer> {
	
	
	    @Query("SELECT m FROM Members m WHERE m.user.id = :id")
	    List<Members> getAllMemberEntityByUserId(@Param("id") int id);
	    
	    Members findById(int id);
	    
	    @Modifying(clearAutomatically = true)
	    @Query("UPDATE Members m SET m.name = :name, m.identity_type = :identityType, " +
	           "m.identity_number = :identityNumber, m.vaccine_name = :vaccineName, " +
	           "m.no_of_doses = :noOfDoses WHERE m.id = :id")
	    int updateMemberEntity(@Param("id") int id,
	                           @Param("name") String name,
	                           @Param("identityType") String identityType,
	                           @Param("identityNumber") String identityNumber,
	                           @Param("vaccineName") String vaccineName,
	                           @Param("noOfDoses") int noOfDoses);
	    
	    List<Members> findAllByUserId(int userId);


}
