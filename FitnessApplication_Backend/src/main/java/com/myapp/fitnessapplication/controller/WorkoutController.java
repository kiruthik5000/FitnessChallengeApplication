package com.myapp.fitnessapplication.controller;

import com.myapp.fitnessapplication.entity.Workout;
import com.myapp.fitnessapplication.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/workouts/{userId}")
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    //get mappings
    @GetMapping("/getworkouts")
    public List<Workout> getWorkouts(@PathVariable int userId) {return workoutService.getAllWorkouts(userId);}

    // post mapping
    @PostMapping("/addworkouts")
    public String addWorkout(@PathVariable int userId, @RequestBody Workout workout) {return workoutService.saveWorkout(userId, workout);}

    //put mappings
    @PutMapping("/changesession")
        public String changeSession(@PathVariable int userId, @RequestParam int id,@RequestParam String newsession) {return workoutService.changeSession(userId, id, newsession);}
    @PutMapping("/changereps")
    public String changeReps(@PathVariable int userId, @RequestParam int id, int reps, int sets){return workoutService.changeReps(userId, id, reps, sets);}

    // delete mappings
    @DeleteMapping("/deleteallworkouts")
    public String deleteAll(@PathVariable int userId) {return workoutService.deleteWorkout(userId);}
    @DeleteMapping("/deleteaworkout")
    public String deleteOne(@PathVariable int userId, @RequestParam int id) {return workoutService.deleteAWorkout(userId, id);}
}
