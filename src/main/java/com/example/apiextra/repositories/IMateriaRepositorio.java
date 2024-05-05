package com.example.apiextra.repositories;

import com.example.apiextra.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaRepositorio extends JpaRepository<Materia, String> {
}
