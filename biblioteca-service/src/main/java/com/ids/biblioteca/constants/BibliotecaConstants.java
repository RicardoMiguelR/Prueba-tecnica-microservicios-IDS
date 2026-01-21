package com.ids.biblioteca.constants;

// Clase de constantes del Microservicio 1 ->
public final class BibliotecaConstants {
    // Constructor privado para evitar intancias inncesarias:
    private BibliotecaConstants() {}

    // Constantes:
    // aforo maximo:
    public static final int AFORO_MAXIMO = 10;
    // longitud de codigoUsuario alfanumerico:
    public static final String CODIGO_USUARIO_REGEX_VALIDACION = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$";

    // Para los headers:
    public static final String HEADER_MY_FLAG_DELAY = "MyFlag";

    // Codigos http:
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_UNPROCESSABLE_ENTITY = 422;

    // respuestas de error:
    public static final String ERROR_USUARIO_NO_ENCONTRADO = "El usuario no fue encontrado";
    public static final String ERROR_USUARIO_DENTRO_DE_BIBLIOTECA = "¡Este usuario ya se encuentra dentro de la biblioteca!";
    public static final String ERROR_AFORO_MAXIMO = "El aforo máximo fue alcanzado";
    public static final String ERROR_CODIGO_USUARIO_INVALIDO = "El código debe ser de 8 caracteres alfanumericos y contener al menos un numero y/o letra";
}
