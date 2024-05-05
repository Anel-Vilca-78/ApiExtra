package com.example.apiextra.services.interfaces;

import com.example.apiextra.controllers.dtos.requests.CreateMateriaRequest;
import com.example.apiextra.controllers.dtos.responses.BaseResponse;

public interface IMateriaService {
    BaseResponse createMateria(CreateMateriaRequest request);
}
