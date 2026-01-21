package com.ids.biblioteca.exception;

// Clase para manejar el JSON
public class ErrorResponse {
    // atributos:
    private int code;
    private String type;
    private long timestamp;
    private String details;

    // constructor:
    public ErrorResponse(int code, String type, String details) {
        this.code = code;
        this.type = type;
        this.details = details;
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    // Metodos getters ->
    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }
}
