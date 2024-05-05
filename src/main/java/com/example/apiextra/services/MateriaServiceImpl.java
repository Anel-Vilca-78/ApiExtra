package com.example.apiextra.services;

import com.example.apiextra.controllers.dtos.requests.CreateMateriaRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;
import com.example.apiextra.controllers.dtos.responses.CreateMateriaResponse;
import com.example.apiextra.entities.Estudiante;
import com.example.apiextra.entities.Materia;
import com.example.apiextra.repositories.IEstudianteRepositorio;
import com.example.apiextra.repositories.IMateriaRepositorio;
import com.example.apiextra.services.interfaces.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImpl implements IMateriaService {
    @Autowired
    private IMateriaRepositorio repositorio;

    @Autowired
    private IEstudianteRepositorio estudianteRepositorio;

    @Override
    public BaseResponse createMateria(CreateMateriaRequest request) {
        Materia materia = from(request);

        return BaseResponse.builder()
                .data(from(repositorio.save(materia)))
                .message("Materia creada correctamente")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED).build();
    }


    private Materia from(CreateMateriaRequest request) {
        Materia materia = new Materia();

        materia.setNombre(request.getNombre());
        materia.setHoras(request.getHoras());

        return materia;

    }

    private CreateMateriaResponse from(Materia materia) {
        CreateMateriaResponse response = new CreateMateriaResponse();

        response.setId(materia.getId());
        response.setNombre(materia.getNombre());
        response.setHoras(materia.getHoras());

        return response;
    }

}
