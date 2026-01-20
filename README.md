# Prueba Técnica - Microservicios Biblioteca

## Descripción
Este proyecto contiene dos microservicios interconectados desarrollados con Java 8 y Spring Boot 2.7.18:

1. **biblioteca-service**: Microservicio de control de acceso a biblioteca (CRUD con H2)
2. **biblioteca-client**: Microservicio cliente que consume biblioteca-service usando Feign

## Tecnologías
- Java 8
- Spring Boot 2.7.18
- Maven
- H2 Database
- Spring Data JPA
- OpenFeign
- JUnit 5 + Mockito

## Estructura del Proyecto
```
ms-ids-prueba/
├── biblioteca-service/     # Microservicio 1 - Backend
└── biblioteca-client/      # Microservicio 2 - Cliente Feign
```

## Autor
Ricardo Miguel - Prueba Técnica Desarrollador Java Backend Jr
