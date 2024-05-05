package com.example.apiextra.repositories;

import com.example.apiextra.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorRepositorio extends JpaRepository<Tutor, String> {
}
