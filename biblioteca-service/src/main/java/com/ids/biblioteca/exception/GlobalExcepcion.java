package com.ids.biblioteca.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Clase para manejar los codigos http ->
@RestControllerAdvice // <- anotacion que permite manejar excepciones globalmente
public class GlobalExcepcion {
    @ExceptionHandler(ExcepcionNegocio.class)
    public ResponseEntity<ErrorResponse> manejarExcepcionNegocio(ExcepcionNegocio excepcionNegocio) {
        ErrorResponse error = new ErrorResponse(excepcionNegocio.getStatusCode(), "Error", excepcionNegocio.getMessage());

        // devolvemos el json
        return ResponseEntity.status(excepcionNegocio.getStatusCode()).body(error);
    }
}
