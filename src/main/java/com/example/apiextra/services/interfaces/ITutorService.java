package com.example.apiextra.services.interfaces;

import com.example.apiextra.controllers.dtos.EstudianteDTO;
import com.example.apiextra.controllers.dtos.requests.CreateTutorRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.entities.Tutor;

import java.util.List;

public interface ITutorService {
    BaseResponse createTutor(CreateTutorRequest request);
    List<Tutor> findAllTutors();
    List<EstudianteDTO> findAllEstudiantesByTutorId(String tutorId);
}
