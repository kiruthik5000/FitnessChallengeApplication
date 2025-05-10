package com.myapp.fitnessapplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    @Id
    private int trainerId;
    private String name;
    private String specialization;
    private double rating;
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("trainerBackreference") // Manages the relationship in JSON serialization
    private List<UserDetails> users;
}
