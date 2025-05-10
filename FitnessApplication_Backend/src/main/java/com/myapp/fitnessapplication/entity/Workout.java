package com.myapp.fitnessapplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "workout")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne // many workout -> users
    @JsonBackReference("workoutBackreference")
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails userDetails;
    private String sessionType;
    private String workoutName;
    private String workoutDescription;
    private int sets;
    private int reps;
}
