package com.ids.consulta.service;

import com.ids.consulta.client.BibliotecaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.fallback.FallbackMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// Clase para logica de negocio (capa de servicio) ->
@Service
public class ConsultaService {

    private final BibliotecaClient bibliotecaClient;

    // Inyeccion por constructor:
    public ConsultaService(BibliotecaClient bibliotecaClient) {
        this.bibliotecaClient = bibliotecaClient;
    }

    // Se agrega el circuit breacker (fallback):
    @CircuitBreaker(name = "bibliotecaCB", fallbackMethod = "fallbackUsuarios")
    public List<Map<String, Object>> consultarUsuarios(String myFlag) { // <- Se agrega el delay desde aqui ms2 "myFlag" *
        return bibliotecaClient.obtenerUsuarios(myFlag);
    }

    // fallback con respuesta generica (fallback identifica ms1 lento o no disponible):
    public List<Map<String, Object>> fallbackUsuarios(String myFlag, Throwable excepcion) {
        // Agregamos una condicion, en la cual identifica si el servidor ms1 no esta levantado/cayo o esta tardando mas de lo normal (delay):
        if (excepcion.getCause() instanceof java.net.ConnectException) {
            throw new RuntimeException("El Microservicio 1 se encuentra fuera de linea");
        }
        throw new RuntimeException("El Microservicio 1 ha tardado mas de lo normal");
    }
}
