package com.myapp.fitnessapplication.repository;

import com.myapp.fitnessapplication.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
    List<Feedback> findAllByUser_UserId(int userId);
}
