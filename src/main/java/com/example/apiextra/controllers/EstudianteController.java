package com.example.apiextra.controllers;

import com.example.apiextra.controllers.dtos.EstudianteDTO;
import com.example.apiextra.controllers.dtos.MateriaDTO;
import com.example.apiextra.controllers.dtos.requests.CreateEstudianteRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.entities.Estudiante;
import com.example.apiextra.entities.Materia;
import com.example.apiextra.services.interfaces.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private IEstudianteService estudianteService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearEstudiante(@RequestBody CreateEstudianteRequest request) {
        BaseResponse response = estudianteService.createEstudiante(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
    //@GetMapping()
    //public ResponseEntity<List<Estudiante>> getallEstudiantes() {
        //List<Estudiante> estudiantes = estudianteService.findAllEstudiantes();
        //return ResponseEntity.ok(estudiantes);
    //}
    @GetMapping()
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.findAllEstudiantes();
        List<EstudianteDTO> dtoList = estudiantes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
    @PutMapping("/{estudianteId}/tutor/{tutorId}")
    public ResponseEntity<EstudianteDTO> asignarTutor(@PathVariable String estudianteId, @PathVariable String tutorId) {
        Estudiante estudiante = estudianteService.asignarTutorAEstudiante(estudianteId, tutorId);
        return ResponseEntity.ok(convertToDTO(estudiante));
    }

    //Método auxiliar para convertir Entidad a DTO
    //private EstudianteDTO convertToDTO(Estudiante estudiante) {
        //return new EstudianteDTO(
                //estudiante.getNombre(),
                //estudiante.getApellido(),
                //estudiante.getMatricula(),
                //estudiante.getGrado(),
                //estudiante.getTutor() != null ? estudiante.getTutor().getNombres() : null
        //);
    //}

    @PutMapping("/{estudianteId}/materias/{materiaId}")
    public ResponseEntity<BaseResponse> asignarMateria(@PathVariable String estudianteId, @PathVariable String materiaId) {
        BaseResponse response = estudianteService.assignMateriaToEstudiante(estudianteId, materiaId);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @GetMapping("/{estudianteId}/materias")
    public ResponseEntity<List<String>> getMateriasByEstudianteId(@PathVariable String estudianteId) {
        List<MateriaDTO> materias = estudianteService.findMateriasByEstudianteId(estudianteId);
        List<String> nombresMaterias = materias.stream()
                .map(MateriaDTO::getNombre)
                .collect(Collectors.toList());
        return ResponseEntity.ok(nombresMaterias);
    }


    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        List<String> materias = estudiante.getMaterias().stream()
                .map(Materia::getNombre)
                .collect(Collectors.toList());

        return new EstudianteDTO(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getMatricula(),
                estudiante.getGrado(),
                estudiante.getTutor() != null ? estudiante.getTutor().getNombres() : null,
                materias
        );
    }

}
