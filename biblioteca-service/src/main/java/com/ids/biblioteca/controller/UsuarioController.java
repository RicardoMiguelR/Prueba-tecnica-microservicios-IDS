package com.ids.biblioteca.controller;

import com.ids.biblioteca.exception.ErrorResponse;
import com.ids.biblioteca.model.Usuario;
import com.ids.biblioteca.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indico que este es mi controlador de endpoints (JSON) ->
@RestController
@RequestMapping("/usuarios") // <- endpoint
public class UsuarioController {
    // referenciamos el usuario service:
    private final UsuarioService usuarioService;

    // Inyectamos por constructor:
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar entrada de usuarios (POST):
    @PostMapping("/entrada")
    public Usuario registrarEntrada(@RequestParam String codigoUsuario) {
        // retornamos el http exitoso:
        return usuarioService.registrarEntrada(codigoUsuario);
    }

    // Registro de salida de usuario (eliminacion de un usuario) ->
    @PostMapping("/salida")
    public ResponseEntity<ErrorResponse> registrarSalida(@RequestParam String codigoUsuario) {
        // REferenciamos el metodo de registrar salida del service (eliminamos):
        usuarioService.registrarSalidaUsuario(codigoUsuario);

        // respuesta generica de exito:
        ErrorResponse respuestaExito = new ErrorResponse(200, "Success", "El usuario " + codigoUsuario + " ha salido con éxito de la biblioteca");

        // REtornamos la respuesta:
        return ResponseEntity.ok(respuestaExito);
    }

    // Listar usuarios que hay dentro de la biblioteca ->
    @GetMapping
    public List<Usuario> listarUsuarios(@RequestHeader(value = "MyFlag", required = false) String myFlag) throws InterruptedException {
        // Si el header viene como true dejamos los 8 segundos:
        if ("true".equalsIgnoreCase(myFlag)) {
            Thread.sleep(8000); // <- delay para congelar el response 8 segundos *
        }

        // retornamos la lista:
        return usuarioService.listarUsuariosDentro();
    }
}
