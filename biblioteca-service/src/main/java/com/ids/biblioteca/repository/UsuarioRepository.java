package com.ids.biblioteca.repository;

import com.ids.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* Indico que esta interface es un repositorio que implementa JPARepository,
 referenciando a obj tipo Usuario con tipo de dato Long ->
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Agrego el Optional para los valores inexistentes o nulos (excepciones):
    Optional<Usuario> findByCodigoUsuario(String codigoUsuario);
}
