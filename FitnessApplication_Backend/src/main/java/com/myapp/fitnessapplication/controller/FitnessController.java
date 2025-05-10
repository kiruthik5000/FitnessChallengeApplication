package com.myapp.fitnessapplication.controller;

import com.myapp.fitnessapplication.entity.FitnessDetails;
import com.myapp.fitnessapplication.service.FitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FitnessController {
    @Autowired
    FitnessService fitnessService;

    // get mappings
    @GetMapping("/{userId}/fitnessdetails")
    public FitnessDetails getFitnessDetails(@PathVariable("userId") int userId) {return fitnessService.getFitnessDetails(userId);}

    @PostMapping("/{userId}/addfitnessdetails")
    public FitnessDetails addFitnessDetails(@PathVariable("userId") int userId, @RequestBody FitnessDetails fitnessDetails){return fitnessService.addFitnessDetails(userId,fitnessDetails);}

    @PutMapping("/{userId}/updatedetails")
    public FitnessDetails updateFitnessDetails(@PathVariable("userId") int userId, @RequestBody FitnessDetails fitnessDetails) {return fitnessService.addFitnessDetails(userId,fitnessDetails);}

    @DeleteMapping("/{userId}/deletefitnessdetails")
    public String deleteFitnessDetails(@PathVariable("userId") int userId){return fitnessService.deleteFitnessDetails(userId);}

}
