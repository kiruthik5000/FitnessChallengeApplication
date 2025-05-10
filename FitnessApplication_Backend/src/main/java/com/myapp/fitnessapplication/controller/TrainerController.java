package com.myapp.fitnessapplication.controller;

import com.myapp.fitnessapplication.entity.Trainer;
import com.myapp.fitnessapplication.entity.UserDetails;
import com.myapp.fitnessapplication.service.TrainerService;
import com.myapp.fitnessapplication.service.UserService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    // get mappings
    @GetMapping("/getalltrainer")
    public List<Trainer> getAllTrainer() {return trainerService.getAllTrainers();}
    @GetMapping("/{trainer_id}")
    public Trainer getTrainer(@PathVariable int trainer_id){return trainerService.getTrainerById(trainer_id);}
    // post mapping
    @PostMapping("/addtrainer")
    public Trainer addTrainer(@RequestBody Trainer trainer) {return trainerService.addTrainer(trainer);}

    //put mapping
    @PutMapping("/{trainer_id}/updatetrainer")
    public String updateTrainer(@PathVariable int trainer_id, @RequestParam String specialization) {return trainerService.updateTrainer(trainer_id,specialization);}
    @PutMapping("/{trainer_id}/updaterating")
    public String updateTrainerRating(@PathVariable int trainer_id, @RequestParam double rating) {return trainerService.updateTrainerRating(trainer_id,rating);}

    // delete mapping
    @DeleteMapping("/{trainer_id}")
    public String deleteTrainer(@PathVariable int trainer_id) {return trainerService.deleteTrainer(trainer_id);}

    // pagenation
    @GetMapping("/pagenation")
    public List<Trainer> showtraniers(@RequestParam int page, @RequestParam int size, @RequestParam String type) {return trainerService.showAllTrainers(type,page,size);}

    @PostMapping("/trainer/pushWorkout")
    public String pushWorkoutToUser(@RequestBody TrainerService.pushWorkout workout) {return trainerService.pushWorkoutToUser(workout);}

    // custom Query Annotation
    @GetMapping("/rating")
    public List<Trainer> getAllTrainerByRating(@RequestParam double rating) {return trainerService.customFindall(rating);}
}
