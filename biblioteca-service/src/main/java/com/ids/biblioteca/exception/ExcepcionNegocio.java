package com.ids.biblioteca.exception;

// Clase para no usar runtineexcepcion simple ->
public class ExcepcionNegocio extends RuntimeException {
    private final int statusCodigo;

    // constructor:
    public ExcepcionNegocio(int statusCodigo, String message) {
        super(message);
        this.statusCodigo = statusCodigo;
    }

    // metodo getter ->
    public int getStatusCodigo() {
        return statusCodigo;
    }
}
