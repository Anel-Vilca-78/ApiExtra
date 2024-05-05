package com.example.apiextra.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTutorRequest {

    private String nombres;
    private String apellidos;
    private int edad;

}
