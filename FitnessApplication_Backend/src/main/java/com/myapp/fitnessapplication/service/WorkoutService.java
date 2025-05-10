package com.myapp.fitnessapplication.service;

import com.myapp.fitnessapplication.entity.Workout;
import com.myapp.fitnessapplication.repository.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepo workoutRepo;

    // get mappings
    public List<Workout> getAllWorkouts(int userId) {
        try {
            return workoutRepo.findAllByUserDetails_UserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    // post mappings
    public String saveWorkout(int userId, Workout workout) {
        try {
            workoutRepo.save(workout);
            return "Workout saved Successfully";
        } catch (Exception e) {
            return "Cannot save workout";
        }
    }

    // put mappings
    public String changeSession(int userId, int id, String session) {
        try {
            Workout optionalWorkout = workoutRepo.findByIdAndUserDetails_UserId(id, userId);
            if (optionalWorkout == null) {
                return "Workout not found for userId: " + userId + " and workoutId: " + id;
            }
            optionalWorkout.setSessionType(session);
            workoutRepo.save(optionalWorkout);
            return "Session changed Successfully";
        } catch (Exception e) {
            return "Error changing session";
        }
    }

    public String changeReps(int userId, int id, int reps, int sets) {
        try {
            Workout optionalWorkout = workoutRepo.findByIdAndUserDetails_UserId(id, userId);
            if (optionalWorkout == null) {
                return "Workout not found for userId: " + userId + " and workoutId: " + id;
            }
            optionalWorkout.setReps(reps);
            optionalWorkout.setSets(sets);
            workoutRepo.save(optionalWorkout);
            return "Reps changed Successfully";
        } catch (Exception e) {
            return "Error changing reps";
        }
    }

    // delete mappings
    public String deleteWorkout(int userId) {
        try {
            List<Workout> workouts = workoutRepo.findAllByUserDetails_UserId(userId);
            if (workouts.isEmpty()) {
                return "Workout not found for userId: " + userId;
            }
            workoutRepo.deleteAll(workouts);
            return "Workout deleted Successfully";
        } catch (Exception e) {
            return "Error deleting workout";
        }
    }

    public String deleteAWorkout(int userId, int id) {
        try {
            if (workoutRepo.findByIdAndUserDetails_UserId(id, userId) == null) {
                return "Workout not found for userId: " + userId + " and workoutId: " + id;
            }
            workoutRepo.deleteById(id);
            return "Workout deleted Successfully";
        } catch (Exception e) {
            return "Error deleting workout";
        }
    }
//    public record customWorkout(int trainerId, List<Workout> workout) {}
//
//    //qweryAnnotation
//    public customWorkout getCustomWorkout(int trainerId) {
//        try {
//           return workoutRepo.fin
//        }catch (Exception e){
//            return null;
//        }
//    }
}
