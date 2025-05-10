package com.myapp.fitnessapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // much feedback  -> user
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("feedbackBackreference")
    private UserDetails user;

    @ManyToOne // much feedback -> trainer
    @JoinColumn(name = "trainer_id", nullable = false)
    @JsonBackReference("trainerFeedback_backreference")
    private Trainer trainer;
    private String feedback;
    private double rating;
}
