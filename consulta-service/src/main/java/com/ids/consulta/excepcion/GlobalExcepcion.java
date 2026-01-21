package com.ids.consulta.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ids.consulta.constants.ConsultaConstants.HTTP_SERVER_INTERNAL_ERROR;

// Clase para manejar excepciones globales ->
@RestControllerAdvice
public class GlobalExcepcion {

    // metodo para manerar excepcion y la respuesta http 500:
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> manejarError(RuntimeException runtimeException) {
        ErrorResponse error = new ErrorResponse(
                HTTP_SERVER_INTERNAL_ERROR,
                "Error",
                System.currentTimeMillis(),
                runtimeException.getMessage()
        );

        // REtornarlo:
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
