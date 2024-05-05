package com.example.apiextra.entities.pivots;

import com.example.apiextra.entities.Estudiante;
import com.example.apiextra.entities.Materia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estudiantes_materias")
@Getter
@Setter

public class EstudianteMateria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Materia materia;
}
