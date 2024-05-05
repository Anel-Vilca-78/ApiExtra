package com.example.apiextra.repositories;

import com.example.apiextra.entities.pivots.EstudianteMateria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteMateriaRepositorio extends JpaRepository<EstudianteMateria, String> {
}
