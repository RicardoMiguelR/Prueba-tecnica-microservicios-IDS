package com.ids.biblioteca.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Clase para manejar los codigos http ->
public class GlobalExcepcion {
    @ExceptionHandler(ExcepcionNegocio.class)
    public ResponseEntity<ApiError> manejarExcepcionNegocio(ExcepcionNegocio excepcionNegocio) {
        ApiError apiError = new ApiError(
                excepcionNegocio.getStatusCodigo(),
                excepcionNegocio.getMessage()
        );

        // retornamos el response:
        return ResponseEntity.status(excepcionNegocio.getStatusCodigo()).body(apiError);
    }

}
