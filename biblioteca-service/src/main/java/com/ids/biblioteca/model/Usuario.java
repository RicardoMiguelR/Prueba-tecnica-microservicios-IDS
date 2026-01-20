package com.ids.biblioteca.model;

import javax.persistence.*;

// Declaro la entidad para usuarios y la nombro "usuarios" ->
@Entity
@Table(name = "usuarios")
public class Usuario {

    // Atributos de la entidad/tabla:

    // Id auto incrementable:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columna codigo_usuario, con valor unioc, no puede ser null y longitud de 8 caracteres:
    @Column(name = "codigo_usuario", nullable = false, unique = true, length = 8)
    private String codigoUsuario;

    // Constructor vacio por que lo necesita JPA ->
    public Usuario() {}

    // Constructor con parametos ->
    public Usuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    // Metodos getters y setters ->
    public Long getId() {
        return id;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
