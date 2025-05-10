package com.myapp.fitnessapplication.service;

import com.myapp.fitnessapplication.entity.Trainer;
import com.myapp.fitnessapplication.entity.UserDetails;
import com.myapp.fitnessapplication.entity.Workout;
import com.myapp.fitnessapplication.repository.TrainerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    @Autowired
    private TrainerRepo trainerRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private WorkoutService workoutService;

    // get mappings
    public List<Trainer> getAllTrainers() {
        try {
            return trainerRepo.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Trainer getTrainerById(int id) {
        try {
            Optional<Trainer> trainer = trainerRepo.findById(id);
            return trainer.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    // post mappings
    public Trainer addTrainer(Trainer trainer) {
        try {
            return trainerRepo.save(trainer);
        } catch (Exception e) {
            return null;
        }
    }

    // put mappings
    public String updateTrainer(int trainerId, String specialization) {
        try {
            Optional<Trainer> trainerOpt = trainerRepo.findById(trainerId);
            if (trainerOpt.isPresent()) {
                Trainer trainer = trainerOpt.get();
                trainer.setSpecialization(specialization);
                trainerRepo.save(trainer);
                return "Trainer updated successfully";
            } else {
                return "Trainer not found for id " + trainerId;
            }
        } catch (Exception e) {
            return "Error updating trainer";
        }
    }

    public String updateTrainerRating(int trainerId, double rating) {
        try {
            Optional<Trainer> trainerOpt = trainerRepo.findById(trainerId);
            if (trainerOpt.isPresent()) {
                Trainer trainer = trainerOpt.get();
                trainer.setRating(rating);
                trainerRepo.save(trainer);
                return "Trainer rating updated successfully";
            } else {
                return "Trainer not found for id " + trainerId;
            }
        } catch (Exception e) {
            return "Error updating trainer rating";
        }
    }

    // delete mappings
    public String deleteTrainer(int trainerId) {
        try {
            if (trainerRepo.existsById(trainerId)) {
                trainerRepo.deleteById(trainerId);
                return "Trainer deleted successfully";
            }
            return "Trainer not found for id " + trainerId;
        } catch (Exception e) {
            return "Error deleting trainer";
        }
    }

    // pagenation
    public List<Trainer> showAllTrainers(String type, int page, int size) {
        Pageable pg = PageRequest.of(page, size, Sort.by(type).ascending());
        return trainerRepo.findAllBy(pg).getContent();
    }

    // starts_with
    public List<Trainer> trainerStartsWith(String prefix) {
        try {
            return trainerRepo.findAllByNameStartsWith(prefix);
        }catch (Exception e) {
            System.out.println("Error in trainer startsWith");
            return null;
        }
    }

    public record pushWorkout(int trainerId, int userId, Workout workout) {};

    public String pushWorkoutToUser(pushWorkout workout) {
        try{
            Workout curWorkout = workout.workout;
            curWorkout.setUserDetails(userService.getUserById(workout.userId));
            workoutService.saveWorkout(workout.userId, curWorkout);
            return "Workout pushed successfully";
        }catch (Exception e) {
            return "Error pushing workout" + e;
        }
    }

    // query Annotation
    public List<Trainer> customFindall(double rating) {
        try {
            return trainerRepo.findAllByRating(rating);
        }catch (Exception e){
            return null;
        }
    }
}