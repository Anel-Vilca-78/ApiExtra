package com.example.apiextra.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaDTO {
    private String id;
    private String nombre;

    // Constructor, getters y setters
    public MateriaDTO(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
