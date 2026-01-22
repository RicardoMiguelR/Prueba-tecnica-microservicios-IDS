package com.ids.consulta.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.ids.consulta.constants.ConsultaConstants.HTTP_SERVER_INTERNAL_ERROR;
import static com.ids.consulta.constants.ConsultaConstants.HTTP_CLIENT_INTERNAL_ERROR;

import com.ids.consulta.excepcion.Ms1FueraDeLineaExcepcion;

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

    // respuesta 400 cuando cae ms1:
    @ExceptionHandler(Ms1FueraDeLineaExcepcion.class)
    public ResponseEntity<ErrorResponse> manejarMs1FueraDeLinea(Ms1FueraDeLineaExcepcion ms1FueraDeLineaExcepcion) {
        ErrorResponse error = new ErrorResponse(
                HTTP_CLIENT_INTERNAL_ERROR,
                "Error",
                System.currentTimeMillis(),
                ms1FueraDeLineaExcepcion.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
