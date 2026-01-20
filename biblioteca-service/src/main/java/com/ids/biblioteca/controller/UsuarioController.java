package com.ids.biblioteca.controller;

import com.ids.biblioteca.model.Usuario;
import com.ids.biblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indico que este es mi controlador de endpoints (JSON) ->
@RestController
@RequestMapping("/usuarios") // <- endpoint
public class UsuarioController {
    private final UsuarioService usuarioService;

    // Inyectamos por constructor:
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Registrar entrada de usuarios (POST):
    @PostMapping("/entrada/{codigoUsuario}")
    public ResponseEntity<Usuario> registrarEntrada(@PathVariable String codigoUsuario) {
        // referenciamos al metodo registrar del service:
        Usuario usuario = usuarioService.registrarEntrada(codigoUsuario);
        // retornamos el http exitoso:
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // Registro de salida de usuario ->
    @DeleteMapping("/salida/{codigoUsuario}")
    public ResponseEntity<Void> registrarSalida(@PathVariable String codigoUsuario) {
        // REferenciamos el metodo de registrar salida del service:
        usuarioService.registrarSalidaUsuario(codigoUsuario);
        return ResponseEntity.noContent().build();
    }

    // Listar usuarios que hay dentro de la biblioteca ->
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        // retornamos el response de usuarios que hay:
        return ResponseEntity.ok(usuarioService.listarUsuariosDentro());
    }
}
