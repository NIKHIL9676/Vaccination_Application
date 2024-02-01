package com.example.practise.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.practise.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	User findById(int id);

	

}
