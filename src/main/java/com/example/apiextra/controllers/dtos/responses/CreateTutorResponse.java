package com.example.apiextra.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTutorResponse {
    private String id;
    private String nombres;
    private String apellidos;
    private int edad;
}
