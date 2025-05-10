package com.myapp.fitnessapplication.controller;

import com.myapp.fitnessapplication.entity.Feedback;
import com.myapp.fitnessapplication.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    // get mappings
    @GetMapping("/getall")
    public List<Feedback> getAllFeedback(){return feedbackService.findAll();}

    @GetMapping("/users/{userId}")
    public List<Feedback> findbyUser(@PathVariable int userId){return feedbackService.findByUser(userId);}

//    @GetMapping("/{trainerId}")
//    public List<Feedback> findbyTrainer(@PathVariable int trainerId){return feedbackService.findByTrainer(trainerId);}

    // post mappings
    @PostMapping("/addfeedback")
    public Feedback addFeedback(@RequestBody Feedback feedback){return feedbackService.addFeedbacks(feedback);}

    @PutMapping("/{trainerId}/updaterating")
    public String updaterating(@PathVariable int trainerId, @RequestParam double rating){return feedbackService.updateRating(trainerId,rating);}

    @PutMapping("/{trainerId}/updatefeedback")
    public String updateFeedback(@PathVariable int trainerId, @RequestParam String feedback){return feedbackService.updateFeedback(trainerId,feedback);}

    // delete mappings
    @DeleteMapping("/{feedback_id}")
    public String deleteFeedback(@PathVariable int feedback_id){return feedbackService.deleteFeedback(feedback_id);}
    

}
