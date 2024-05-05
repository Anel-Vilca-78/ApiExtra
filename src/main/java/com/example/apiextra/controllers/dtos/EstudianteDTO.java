package com.example.apiextra.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * EstudianteDTO es un objeto de transferencia de datos para manipular la información de estudiantes.
 * Incluye detalles básicos del estudiante y el nombre de su tutor.
 */

@Getter
@Setter
public class EstudianteDTO {
    private String id;
    private String nombre;
    private String apellido;
    private String matricula;
    private int grado;
    private String nombreTutor;
    private List<String> materiasIds;


    public EstudianteDTO() {}

    public EstudianteDTO(String id, String nombre, String apellido, String matricula, int grado, String nombreTutor,List<String> materiasIds) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
        this.grado = grado;
        this.nombreTutor = nombreTutor;
        this.materiasIds = materiasIds;
    }
}
