package com.myapp.fitnessapplication.repository;

import com.myapp.fitnessapplication.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<UserDetails, Integer> {
    UserDetails findByUserId(Integer userId);
    UserDetails findByUserEmail(String userEmail);
    List<UserDetails> findByUserNameContainingIgnoreCase(String userName);

}
