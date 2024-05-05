package com.example.apiextra.services.interfaces;

import com.example.apiextra.controllers.dtos.EstudianteDTO;
import com.example.apiextra.controllers.dtos.MateriaDTO;
import com.example.apiextra.controllers.dtos.requests.CreateEstudianteRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.entities.Estudiante;

import java.util.List;


public interface IEstudianteService {
    BaseResponse createEstudiante(CreateEstudianteRequest request);
    List<Estudiante> findAllEstudiantes();
    Estudiante asignarTutorAEstudiante(String estudianteId, String tutorId);
    BaseResponse assignMateriaToEstudiante(String estudianteId, String materiaId);
    List<MateriaDTO> findMateriasByEstudianteId(String estudianteId);
}
