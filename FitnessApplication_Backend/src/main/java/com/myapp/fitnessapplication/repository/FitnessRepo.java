package com.myapp.fitnessapplication.repository;

import com.myapp.fitnessapplication.entity.FitnessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessRepo extends JpaRepository<FitnessDetails, Integer> {
    FitnessDetails findByUserDetails_UserId(int userDetailsUserId);
}
