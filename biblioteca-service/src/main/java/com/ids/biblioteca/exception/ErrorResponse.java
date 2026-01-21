package com.ids.biblioteca.exception;

// Clase para manejar el JSON
public class ErrorResponse {
    // atributos:
    private int code;
    private String type;
    private long timestamp;
    private String details;

    // constructor:
    public ErrorResponse(int code, String details) {
        this.code = code;
        this.type = "Error";
        this.timestamp = System.currentTimeMillis() / 1000;
        this.details = details;
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
