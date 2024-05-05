package com.example.apiextra.services;

import com.example.apiextra.controllers.dtos.EstudianteDTO;
import com.example.apiextra.controllers.dtos.requests.CreateTutorRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.controllers.dtos.responses.CreateTutorResponse;
import com.example.apiextra.entities.Estudiante;
import com.example.apiextra.entities.Tutor;
import com.example.apiextra.repositories.ITutorRepositorio;
import com.example.apiextra.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorServiceImpl implements ITutorService {
    @Autowired
    private ITutorRepositorio repositorio;

    @Override
    public BaseResponse createTutor(CreateTutorRequest request) {
        Tutor tutor = from(request);

        return BaseResponse.builder()
                .data(from(repositorio.save(tutor)))
                .message("Tutor creado correctamente")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public List<Tutor> findAllTutors() {
        return repositorio.findAll();
    }

    @Override
    public List<EstudianteDTO> findAllEstudiantesByTutorId(String tutorId) {
        Tutor tutor = repositorio.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));

        return tutor.getEstudiantes().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setMatricula(estudiante.getMatricula());
        dto.setGrado(estudiante.getGrado());
        // Asumiendo que EstudianteDTO tiene estos campos y que Estudiante tiene métodos getter correspondientes
        // Si Estudiante tiene una relación con Tutor, y quieres incluir detalles del tutor en el DTO, agrega esos campos aquí también.
        return dto;
    }


    private Tutor from(CreateTutorRequest request) {
        Tutor tutor = new Tutor();


        tutor.setNombres(request.getNombres());
        tutor.setApellidos(request.getApellidos());
        tutor.setEdad(request.getEdad());

        return tutor;
    }

    private CreateTutorResponse from(Tutor tutor) {
        CreateTutorResponse response = new CreateTutorResponse();

        response.setId(tutor.getId());
        response.setNombres(tutor.getNombres());
        response.setApellidos(tutor.getApellidos());
        response.setEdad(tutor.getEdad());

        return response;
    }

}
