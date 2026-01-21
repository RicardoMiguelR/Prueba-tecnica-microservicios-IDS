package com.ids.consulta.excepcion;

// Clase que maneja errores (execpciones) globalmente ->
public class ErrorResponse {
    // atributos:
    private int code;
    private String type;
    private long timestamp;
    private String details;

    // Constructor parametrizado:
    public ErrorResponse(int code, String type, long timestamp, String details) {
        this.code = code;
        this.type = type;
        this.timestamp = timestamp;
        this.details = details;
    }

    // Metodos getters:
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
