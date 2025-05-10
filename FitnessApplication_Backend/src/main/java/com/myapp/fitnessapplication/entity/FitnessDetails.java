package com.myapp.fitnessapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fitness_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FitnessDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // one fitness details -> one user
    @JoinColumn(name = "user_id")
    private UserDetails userDetails;

    private int age;
    private String gender;
    private int height;
    private int weight;
}
