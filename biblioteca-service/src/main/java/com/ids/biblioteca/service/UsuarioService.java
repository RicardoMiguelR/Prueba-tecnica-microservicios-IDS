package com.ids.biblioteca.service;

import com.ids.biblioteca.exception.ExcepcionNegocio;
import com.ids.biblioteca.model.Usuario;
import com.ids.biblioteca.repository.UsuarioRepository;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.ids.biblioteca.constants.BibliotecaConstants.*;

// Indico que esta es mi clase de Servicio, mi logica de negocio ->
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    // se inyecta el repository por el constructor:
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // metodo para validar el codigo ->
    public void validarCodigoUsuario(String codigoUsuario) {
        // validamos con regex:
        final Pattern REGEX_ALFANUMERICO = Pattern.compile(CODIGO_USUARIO_REGEX_VALIDACION, Pattern.CASE_INSENSITIVE); // <- regex para la validacion del codigoUsuario *
        if (!REGEX_ALFANUMERICO.matcher(codigoUsuario.trim()).matches()) {
            throw new ExcepcionNegocio(HTTP_UNPROCESSABLE_ENTITY, ERROR_CODIGO_USUARIO_INVALIDO);
        }
    }

    // Metodo para registrar la entrada de usuarios ->
    public Usuario registrarEntrada(String codigoUsuario) {
        // VAlidamos el codigo (8 caracteres):
        validarCodigoUsuario(codigoUsuario);
        // metodo para validar el aforo:
        consultarAforo();

        // agrego el optional para buscar mediante el metodo al usuario por su codigoUsuario:
        Optional<Usuario> existeUsuario = usuarioRepository.findByCodigoUsuario(codigoUsuario);

        // condicion para arrojar error si ya esta dentro el usuario:
        if (existeUsuario.isPresent()) {
            throw new ExcepcionNegocio(HTTP_UNAUTHORIZED, ERROR_USUARIO_DENTRO_DE_BIBLIOTECA);
        }

        // Creamos un objeto de tipo Usaurio:
        Usuario usuario = new Usuario();
        usuario.setCodigoUsuario(codigoUsuario);
        //usuario.setFechaEntrada(LocalDateTime.now()); // En duda para agregar ¿?

        // Transaccion (guardamos usuario):
        return usuarioRepository.save(usuario);
    }

    // Metodo para registrar la salida ->
    public void registrarSalidaUsuario(String codigoUsuario) {
        Usuario usuario = usuarioRepository.findByCodigoUsuario(codigoUsuario).orElseThrow(
                () -> new ExcepcionNegocio(HTTP_NOT_FOUND, ERROR_USUARIO_NO_ENCONTRADO)
        );

        // Transaccion: eliminar usuario:
        usuarioRepository.delete(usuario);
    }

    // Consultar usuarios que hay dentro ->
    public List<Usuario> listarUsuariosDentro() {
        return usuarioRepository.findAll();
    }

    // VAlidar y consultar el aforo que existe ->
    private void consultarAforo() {
        long usuariosDentro = usuarioRepository.count();

        // Condicion para el aforo maximo:
        if (usuariosDentro >= AFORO_MAXIMO) {
            throw new ExcepcionNegocio(HTTP_UNAUTHORIZED, ERROR_AFORO_MAXIMO);
        }
    }
}
