package com.example.apiextra.controllers;

import com.example.apiextra.controllers.dtos.requests.CreateMateriaRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.services.interfaces.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @PostMapping()
    public ResponseEntity<BaseResponse> crearMateria(@RequestBody CreateMateriaRequest request) {
        BaseResponse response = materiaService.createMateria(request);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


}
