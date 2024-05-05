package com.example.apiextra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tutores")
@Getter
@Setter
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombres;
    private String apellidos;
    private int edad;

    @OneToMany(mappedBy = "tutor")
    private List<Estudiante> estudiantes;
}
