package com.myapp.fitnessapplication.repository;

import com.myapp.fitnessapplication.entity.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Integer> {
    Page<Trainer> findAllBy(Pageable pg);

    List<Trainer> findAllByNameStartsWith(String prefix);

    @Query("select t from Trainer t where t.rating >= :rating")
    List<Trainer> findAllByRating(@Param("rating") double rating);
}
