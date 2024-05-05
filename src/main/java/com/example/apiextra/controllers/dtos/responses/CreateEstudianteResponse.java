package com.example.apiextra.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEstudianteResponse {
    private String id;
    private String nombre;
    private String apellido;
    private String matricula;
    private int grado;
}
