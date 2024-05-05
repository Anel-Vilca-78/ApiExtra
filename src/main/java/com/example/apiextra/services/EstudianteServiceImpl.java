package com.example.apiextra.services;

import com.example.apiextra.controllers.dtos.EstudianteDTO;
import com.example.apiextra.controllers.dtos.MateriaDTO;
import com.example.apiextra.controllers.dtos.requests.CreateEstudianteRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.controllers.dtos.responses.CreateEstudianteResponse;
import com.example.apiextra.entities.Estudiante;
import com.example.apiextra.entities.Materia;
import com.example.apiextra.entities.Tutor;
import com.example.apiextra.entities.pivots.EstudianteMateria;
import com.example.apiextra.repositories.IEstudianteMateriaRepositorio;
import com.example.apiextra.repositories.IEstudianteRepositorio;
import com.example.apiextra.repositories.IMateriaRepositorio;
import com.example.apiextra.repositories.ITutorRepositorio;
import com.example.apiextra.services.interfaces.IEstudianteService;
import com.example.apiextra.services.interfaces.ITutorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements IEstudianteService {
    @Autowired
    private IEstudianteRepositorio repositorio;

    @Autowired
    private ITutorRepositorio tutorRepositorio;

    @Autowired
    private IMateriaRepositorio materiaRepositorio;

    @Autowired
    private IEstudianteMateriaRepositorio estudianteMateriaRepository;


    @Override
    public BaseResponse createEstudiante(CreateEstudianteRequest request) {
        Estudiante estudiante = from(request);

        return BaseResponse.builder()
                .data(from(repositorio.save(estudiante)))
                .message("Estudiante creado correctamente")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }

    @Override
    public List<Estudiante> findAllEstudiantes() {
        return repositorio.findAll();
    }

    //esto añadi
    public Estudiante asignarTutorAEstudiante(String estudianteId, String tutorId) { // Elimina @Override si no estás sobrescribiendo
        Estudiante estudiante = repositorio.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Tutor tutor = tutorRepositorio.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor no encontrado"));
        estudiante.setTutor(tutor);
        return repositorio.save(estudiante);
    }
    //
    @Transactional
    public BaseResponse assignMateriaToEstudiante(String estudianteId, String materiaId) {
        Estudiante estudiante = repositorio.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Materia materia = materiaRepositorio.findById(materiaId)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));

        // Comprobar si la relación ya existe
        boolean exists = estudiante.getEstudianteMaterias().stream()
                .anyMatch(em -> em.getMateria().getId().equals(materiaId));
        if (exists) {
            return BaseResponse.builder()
                    .data(null)
                    .message("Materia ya está asignada a este estudiante")
                    .success(false)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }

        // Crear la nueva relación EstudianteMateria
        EstudianteMateria estudianteMateria = new EstudianteMateria();
        estudianteMateria.setEstudiante(estudiante);
        estudianteMateria.setMateria(materia);
        estudianteMateriaRepository.save(estudianteMateria);

        return BaseResponse.builder()
                .data(null)
                .message("Materia asignada correctamente")
                .success(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public List<MateriaDTO> findMateriasByEstudianteId(String estudianteId) {
        Estudiante estudiante = repositorio.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        return estudiante.getMaterias().stream()
                .map(materia -> new MateriaDTO(materia.getId(), materia.getNombre()))
                .collect(Collectors.toList());
    }


    private Estudiante from (CreateEstudianteRequest request) {
        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(request.getNombre());
        estudiante.setApellido(request.getApellido());
        estudiante.setMatricula(request.getMatricula());
        estudiante.setGrado(request.getGrado());

        return estudiante;
    }

    private CreateEstudianteResponse from(Estudiante estudiante){
        CreateEstudianteResponse response = new CreateEstudianteResponse();

        response.setId(estudiante.getId());
        response.setGrado(estudiante.getGrado());
        response.setNombre(estudiante.getNombre());
        response.setApellido(estudiante.getApellido());
        response.setMatricula(estudiante.getMatricula());

        return response;
    }
}
