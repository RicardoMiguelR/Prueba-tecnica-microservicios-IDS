# Prueba Técnica IDS - Microservicios - Biblioteca v1.0.1 (actual) 📌

## 🔹 Descripción
**Este proyecto contiene dos microservicios independientes, desarrollados con Java 8 y Spring Boot 2.7.18:**

## 🔹 Tecnologías
- Java 8
- Spring Boot 2.7.18
- Maven
- H2
- Spring Data JPA
- Spring Cloude OpenFeign

---

### 🔹 Microservicio 1 - biblioteca-service
- Provee al MS2 de usuarios
- Simula delay mediante header `MyFlag=true`, entre otras pruebas.

```json
[
    {
        "id": 3,
        "codigoUsuario": "jjjjj788"
    },
    {
        "id": 2,
        "codigoUsuario": "jjjjj7hy"
    },
    {
        "id": 1,
        "codigoUsuario": "lllllo09"
    }
]
```

### 🔹 Microservicio 2 - consulta-service
- Consume al MS1 mediante OpenFeign client
- Ejecuta un Circuit Breaker y fallback para ciertos casos en MS1 (fuera de línea o delay)
- Manejo de errores (excepciones) genérico

```json
{
    "code": 500,
    "type": "Error",
    "timestamp": 1768987368094,
    "details": "El Microservicio 1 se encuentra fuera de línea"
}
```

### 🔹 Ejecución
1. Levantar `biblioteca-service`
2. Levantar `consulta-service`
3. Probar endpoints desde Postman (opción a gusto)

---

## 🔹 Estructura del Proyecto esperado
```
ms-ids-prueba/
├── biblioteca-service/     # Microservicio 1 - Backend
└── biblioteca-client/      # Microservicio 2 - Cliente Feign
```

**Dato**: el proyecto esta almacenado en un solo repositorio, para facilitar la revision y entendimiento de código y estructura, pero cada Microservicio es independiente uno del otro.

## 🔹 Contexto de la prueba

#### Microservicio 1:
Realice un CRUD a base de datos h2 donde se tenga el control de acceso a la biblioteca para controlar el acceso de usuarios a la misma con manejo de excepciones.

- Consideraciones en la biblioteca tenemos espacio estimado que el aforo maximo sea de 10 personas
- El request debe recibir un string  8 decaracteres alfanumerIco el cual se debe de validar y mandar un codigo 422 si no se cumple con esta regla o solo presenta letras.

- En este microservicio tenemos que hacer un select a la tabla para validar los usuarios si el usuario ya esta registrado en la tabla, si no esta dado de alta porder darlo de alta, si el usuario ya existe en la tabla mandar un mensaje de que el usuario ya esta dentro de la Biblioteca.

- Si ya tenemos un aforo maximo de 10 personas, se debe denegar el acceso y eso se debe retornar un codigo 401 Unauthorized.

- Adicional en un metodo post para dar salida a los usuarios que salen de la biblioteca que seria una eliminacion de la base del usuario, se debe de validar que el usuario existe o no. Si exite notificar que existe existe y si se borro de manera correcta. (Si no existe marcar una excepcion)

- Poder realizar una consulta de todos los registros, este Debera de poder recibir un parametro en sus headers "MyFlag=true" el cual hara que la peticion tarde 8 segundos mas en responder (Timer).

- El manejo de excepciones debe de presenatar un formato generico:
    - code: 401
    - type: 'Error'
    - timestamp: '1763045191
    - details: 'El Usuario ya esta dado de alta'

### Microservicio 2:
- Hacer el llamado del Microservicio 1 por medio de feign, para consultar todos los usuarios.
- Si este llamado tarda mas de 5 segundos, abortar y generar un fallback (Circuit break) generando un error 500 con el formato generico antes mencionado.
- Del mismo modo si el Microservicio 1 se encuentra fuera de linea, he indicar cual es la causa en cada uno de los casos.
- El manejo de excepciones debe de presenatar un formato generico:
    - code: 400
    - type: 'Error'
    - timestamp: '1763045191
    - details: 'El MS 1 tardo mas de lo esperado'

* PLUS: 
    - Realizar los MS con Java 1.8 (realizar un respaldo) y poder hacer la migracion a java 21.
    - Realizar pruebas unitarias del MS 1 (Junit jupiter, mockito)
    - Buenas practicas, nomeclaruta de las variables, archivo de constantes, declarar config en YML
