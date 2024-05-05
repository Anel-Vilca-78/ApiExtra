package com.example.apiextra.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEstudianteRequest {
    private String nombre;
    private String apellido;
    private String matricula;
    private int grado;
}
