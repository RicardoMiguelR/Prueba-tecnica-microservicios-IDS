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

// Indico que esta es mi clase de Servicio, mi logica de negocio ->
@Service
public class UsuarioService {
    // variable para el aforo maximo:
    private static final int AFORO_MAXIMO = 10;

    private final UsuarioRepository usuarioRepository;

    // se inyecta el repository por el constructor:
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // metodo para validar el codigo ->
    public void validarCodigoUsuario(String codigoUsuario) {
        // validamos con regex:
        final Pattern REGEX_ALFANUMERICO = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$", Pattern.CASE_INSENSITIVE); // <- regex para la validacion del codigoUsuario *
        if (!REGEX_ALFANUMERICO.matcher(codigoUsuario.trim()).matches()) {
            throw new ExcepcionNegocio(422, "El código debe ser de 8 caracteres alfanumericos y contener al menos un numero y/o letra");
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
            throw new ExcepcionNegocio(401, "¡Este usuario ya se encuentra dentro de la biblioteca!");
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
                () -> new ExcepcionNegocio(404,"El usuario no fue encontrado")
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
            throw new ExcepcionNegocio(401, "El aforo máximo fue alcanzado");
        }
    }
}
