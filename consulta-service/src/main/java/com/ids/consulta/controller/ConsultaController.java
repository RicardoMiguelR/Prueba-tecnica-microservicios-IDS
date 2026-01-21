package com.ids.consulta.controller;

import com.ids.consulta.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.ids.consulta.constants.ConsultaConstants.HEADER_MY_FLAG_DELAY;

// El controlador para maneja de peticiones http ->
@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    private final ConsultaService consultaService;

    // Inyeccion por constructor:
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    // metodo GET para usuarios:
    @GetMapping("/usuarios")
    public ResponseEntity<?> consultarUsuarios(@RequestHeader(value = HEADER_MY_FLAG_DELAY, required = false) String myFlag) { // <- se obtiene del headers el delay "MyFlag" *
        return ResponseEntity.ok(consultaService.consultarUsuarios(myFlag));
    }
}
