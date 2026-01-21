package com.ids.biblioteca.exception;

// Clase para no usar runtineexcepcion simple ->
public class ExcepcionNegocio extends RuntimeException {
    private final int statusCode;

    // constructor:
    public ExcepcionNegocio(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    // metodo getter:
    public int getStatusCode() {
        return statusCode;
    }
}
