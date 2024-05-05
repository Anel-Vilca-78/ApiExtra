package com.example.apiextra.repositories;

import com.example.apiextra.entities.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante,String> {

}
