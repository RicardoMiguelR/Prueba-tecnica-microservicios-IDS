package com.ids.consulta.excepcion;

// Clase para manejar respuesta error cuando ms1 se apaga ->
public class Ms1FueraDeLineaExcepcion extends RuntimeException {

    // Constructor:
    public Ms1FueraDeLineaExcepcion(String message) {
        super(message);
    }
}
