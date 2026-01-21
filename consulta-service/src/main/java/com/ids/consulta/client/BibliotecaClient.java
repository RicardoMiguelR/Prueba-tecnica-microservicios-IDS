package com.ids.consulta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient( // <- declaramos o referenciamos al ms1 biblioteca-service para consumir *
        name = "biblioteca-service",
        url = "http://localhost:8080",
        path = "/api/usuarios" // <- endpoint inicial/base
)

public interface BibliotecaClient {
    // Mapeo de usuarios (peticion http):
    @GetMapping
    List<Map<String, Object>> obtenerUsuarios(@RequestHeader(value = "MyFlag", required = false) String myFlag); // <- delay "MyFlag" *
}
