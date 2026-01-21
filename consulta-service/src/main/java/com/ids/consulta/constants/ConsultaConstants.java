package com.ids.consulta.constants;

// Clase de constantes para el ms2 ->
public final class ConsultaConstants {

    // Constructor privado:
    private ConsultaConstants() {}

    // constantes headers:
    public static final String HEADER_MY_FLAG_DELAY = "MyFlag";

    // Codigos http para ms2:
    public static final int HTTP_SERVER_INTERNAL_ERROR = 500;

    // respuestas de error:
    public static final String ERROR_MS1_TIMEOUT = "El Microservicio 1 ha tardado mas de lo normal";
    public static final String ERROR_MS1_FUERA_DE_LINEA = "El Microservicio 1 se encuentra fuera de línea";
    public static final String ERROR_MS1_GENERICO = "Error al comunicarse con el Microservicio 1"; // <- para uso generico, agregar en algun caso
}
