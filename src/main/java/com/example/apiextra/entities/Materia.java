package com.example.apiextra.entities;

import com.example.apiextra.entities.pivots.EstudianteMateria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "materias")
@Getter
@Setter
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private String horas;

    @OneToMany(mappedBy = "materia")
    private List<EstudianteMateria> estudianteMaterias;
}
