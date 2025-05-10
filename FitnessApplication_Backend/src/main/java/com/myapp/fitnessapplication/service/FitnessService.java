package com.myapp.fitnessapplication.service;

import com.myapp.fitnessapplication.entity.FitnessDetails;
import com.myapp.fitnessapplication.repository.FitnessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessService {
    @Autowired
    private FitnessRepo fitnessRepo;

    // get mappings
    public FitnessDetails getFitnessDetails(int userId) {
        try {
            return fitnessRepo.findByUserDetails_UserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    // post mappings
    public FitnessDetails addFitnessDetails(int userId, FitnessDetails fitnessDetails) {
        try {
            FitnessDetails existingFitnessDetails = fitnessRepo.findByUserDetails_UserId(userId);
            if (existingFitnessDetails != null) {
                existingFitnessDetails.setAge(fitnessDetails.getAge());
                existingFitnessDetails.setWeight(fitnessDetails.getWeight());
                existingFitnessDetails.setHeight(fitnessDetails.getHeight());
                return fitnessRepo.save(existingFitnessDetails);
            }
            return fitnessRepo.save(fitnessDetails);
        } catch (Exception e) {
            return null;
        }
    }

    // delete mapping
    public String deleteFitnessDetails(int userId) {
        try {
            FitnessDetails fitnessDetails = fitnessRepo.findByUserDetails_UserId(userId);
            if (fitnessDetails != null) {
                fitnessRepo.deleteById(userId);
                return "Fitness Details Deleted Successfully";
            } else {
                return "Fitness Details Not Found for the user Id: " + userId;
            }
        } catch (Exception e) {
            return "Error deleting fitness details";
        }
    }
}