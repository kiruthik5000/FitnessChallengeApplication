package com.myapp.fitnessapplication.repository;

import com.myapp.fitnessapplication.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout, Integer> {
    List<Workout> findAllByUserDetails_UserId(int userDetailsUserId);
    Workout findByIdAndUserDetails_UserId(int id, int userDetailsUserId);
}
