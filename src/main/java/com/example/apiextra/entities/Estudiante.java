package com.example.apiextra.entities;

import com.example.apiextra.entities.pivots.EstudianteMateria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "estudiantes")
@Getter
@Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nombre;
    private String apellido;
    private String matricula;
    private int grado;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<EstudianteMateria> estudianteMaterias;

    // Método auxiliar para obtener las materias del estudiante
    public List<Materia> getMaterias() {
        if (estudianteMaterias != null) {
            return estudianteMaterias.stream()
                    .map(EstudianteMateria::getMateria)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // Devolver una lista vacía si estudianteMaterias es nulo
        }
    }
}
