package com.ids.biblioteca.exception;

// Clase para manejar el JSON
public class ApiError {
    // atributos:
    private int codigo;
    private String tipo;
    private Long timestamp;
    private String detalles;

    // constructor:
    public ApiError(int codigo, String detalles) {
        this.codigo = codigo;
        this.tipo = "Error";
        this.timestamp = System.currentTimeMillis() / 1000;
        this.detalles = detalles;
    }

    // Metodos getters y setters ->
    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getDetalles() {
        return detalles;
    }
}
