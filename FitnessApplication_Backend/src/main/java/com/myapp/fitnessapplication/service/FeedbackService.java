package com.myapp.fitnessapplication.service;

import com.myapp.fitnessapplication.entity.Feedback;
import com.myapp.fitnessapplication.repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepo feedbackRepo;

    // get mappings
    public List<Feedback> findAll() {
        try {
            return feedbackRepo.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Feedback> findByUser(int userId) {
        try {
            return feedbackRepo.findAllByUser_UserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    // post mappings
    public Feedback addFeedbacks(Feedback feedback) {
        try {
            return feedbackRepo.save(feedback);
        } catch (Exception e) {
            return null;
        }
    }

    // put mappings
    public String updateRating(int feedback_id, double rating) {
        try {
            Optional<Feedback> feedbackOptional = feedbackRepo.findById(feedback_id);
            if (!feedbackOptional.isPresent()) {
                return "Feedback Not Found";
            }
            Feedback feedback = feedbackOptional.get();
            feedback.setRating(rating);
            feedbackRepo.save(feedback);
            return "Feedback Updated Successfully";
        } catch (Exception e) {
            return "Error updating feedback rating";
        }
    }

    public String updateFeedback(int feedback_id, String feedbackText) {
        try {
            Optional<Feedback> feedbackOptional = feedbackRepo.findById(feedback_id);
            if (!feedbackOptional.isPresent()) {
                return "Feedback Not Found";
            }
            Feedback feedback = feedbackOptional.get();
            feedback.setFeedback(feedbackText);
            feedbackRepo.save(feedback);
            return "Feedback Updated Successfully";
        } catch (Exception e) {
            return "Error updating feedback";
        }
    }

    // delete mappings
    public String deleteAllUserFeedback(int user_id) {
        try {
            List<Feedback> feedbackList = feedbackRepo.findAllByUser_UserId(user_id);
            if (feedbackList.isEmpty()) {
                return "Feedback Not Found";
            }
            feedbackRepo.deleteAll(feedbackList);
            return "Feedback Deleted Successfully";
        } catch (Exception e) {
            return "Error deleting feedback";
        }
    }

    public String deleteFeedback(int feedback_id) {
        try {
            Optional<Feedback> feedbackOptional = feedbackRepo.findById(feedback_id);
            if (!feedbackOptional.isPresent()) {
                return "Feedback Not Found";
            }
            feedbackRepo.delete(feedbackOptional.get());
            return "Feedback Deleted Successfully";
        } catch (Exception e) {
            return "Error deleting feedback";
        }
    }
}