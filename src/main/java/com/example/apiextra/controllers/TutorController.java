package com.example.apiextra.controllers;

import com.example.apiextra.controllers.dtos.EstudianteDTO;
import com.example.apiextra.controllers.dtos.requests.CreateTutorRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.entities.Tutor;
import com.example.apiextra.services.interfaces.IEstudianteService;
import com.example.apiextra.services.interfaces.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private ITutorService tutorService;

    @Autowired
    private IEstudianteService estudianteService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearTutor(@RequestBody CreateTutorRequest request) {
        BaseResponse response = tutorService.createTutor(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<Tutor>> getAllTutors() {
        List<Tutor> tutors = tutorService.findAllTutors();
        return ResponseEntity.ok(tutors);
    }

    @GetMapping("/{tutorId}/estudiantes")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesByTutorId(@PathVariable String tutorId) {
        List<EstudianteDTO> estudiantes = tutorService.findAllEstudiantesByTutorId(tutorId);
        return ResponseEntity.ok(estudiantes);
    }

}
