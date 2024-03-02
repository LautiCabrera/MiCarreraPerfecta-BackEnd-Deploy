# MiCarreraPerfecta-BackEnd

Proyecto final de materia "Programación 2" - BackEnd

Este repositorio corresponde al proyecto final desarrollado para rendir y acreditar la materia de Programación 2 dentro del marco de la carrera como Técnico Superior en Desarrollo de Software. Se dedica al desarrollo de la API que contiene la lógica de negocio necesaria para el funcionamiento de la aplicación web asociada.

<p align="center">
  <img src="https://github.com/LautiCabrera/MiCarreraPerfecta-FrontEnd/blob/main/assets/img/Logo.png?raw=true" alt="MiCarreraPerfecta">
</p>

## ⚙ Versionado de la aplicación ⚙

- **Java Version**: 1.8.
- **JDK**: Openjdk-8-jdk.
- **Spring Boot**: 2.7.9.

![Spring Boot](https://miro.medium.com/v2/resize:fit:1400/1*aXe6MaOyhdIP5WqdPHhSFw.png)

## ⚠ Dependencias utilizadas ⚠

Las dependencias incluidas en el proyecto son las siguientes:

- **spring-boot-starter-data-jpa**: API para crear versiones orientadas a objetos de entidades de base de datos relacionales.
- **spring-boot-starter-validation**: Marco de validación que se basa en JSR-303 e Hibernate Validator.
- **spring-boot-starter-web**: Conjunto de dependencias esenciales para desarrollar aplicaciones web en Spring Boot, incluyendo Spring MVC y un servidor web embebido.
- **spring-boot-devtools**: Herramienta de agilización de desarrollo automático.
- **mysql-connector-j**: Controlador nativo de Java que convierte las llamadas generadas por JDBC en el protocolo de red que utiliza la base de datos de MySQL.
- **lombok**: Librería que permite mostrar información en páginas html de forma sencilla.
- **spring-boot-starter-test**: Dependencia que facilita la escritura y ejecución de pruebas unitarias y de integración.

## ⚙ Base de datos - MER ⚙

<p align="center">
  <img src="https://github.com/LautiCabrera/MiCarreraPerfecta-FrontEnd/blob/main/assets/img/MER.jpeg?raw=true" alt="MER">
</p>

## ⌨️ Variables de entorno primordiales ⌨️

- **spring.datasource.hibernate.dialect**: Define el dialecto de Hibernate a utilizar, que determina cómo Hibernate interactúa con la base de datos subyacente.
- **spring.datasource.url**: Es la URL de conexión a la base de datos, especifica la ubicación de la base de datos a la que la aplicación debe conectarse.
- **spring.datasource.username**: Es el nombre de usuario utilizado para autenticarse en la base de datos.
- **spring.datasource.password**: Es la contraseña asociada al nombre de usuario utilizado para autenticarse en la base de datos.
- **spring.jpa.hibernate.ddl-auto**: Controla la estrategia de generación del esquema de la base de datos por parte de Hibernate, como crear, actualizar o validar el esquema.
- **spring.jpa.show-sql**: Determina si se deben mostrar las consultas SQL generadas por Hibernate en la consola de registro.
- **myCareerPerfect.secretWord** Palabra secreta para generar la encriptación de los id de las carreras.

## 🌐 Endpoints de la API 🌐

### Intelligences

| Métodos | Rutas                                    | Acciones                                            |
|---------|------------------------------------------|-----------------------------------------------------|
| POST    | /intelligencesFilter/procesar-respuestas | Almacena lista de inteligencias.                    |

### Preference

| Métodos | Rutas                                    | Acciones                                            |
|---------|------------------------------------------|-----------------------------------------------------|
| POST    | /preferenceFilter/procesar-respuestas    | Retorna una lista de ramas de estudio.              |

### Branch

| Métodos | Rutas                                    | Acciones                                            |
|---------|------------------------------------------|-----------------------------------------------------|
| POST    | /branchFilter/procesar-respuestas        | Almacena lista de ramas de estudio.                 |

### Career

| Métodos | Rutas                                                              | Acciones                                                  |
|---------|--------------------------------------------------------------------|-----------------------------------------------------------|
| POST    | /branchFilter/procesar-respuestas                                  | Retorna un entero correspondiente al id de la carrera.    |
| GET     | /branchFilter/obtener-carrera-perfecta/{encryptedCareerId}         | Retorna un objeto con la informacion de una carrera.      |
| POST    | /branchFilter/busqueda-carrera                                     | Retorna una lista de objetos con informacion de carreras. |


## 📱 Demo V1.0 📲 

# [MiCarreraPerfecta](https://mi-carrera-perfecta-front-end.vercel.app/)

## 👨‍💻 Autor 👨‍💻

- [@LautiCabrera](https://github.com/LautiCabrera)

## ✨ Contribución ✨

Si deseas contribuir a este proyecto o informar sobre problemas, no dudes en abrir un problema o enviar una solicitud de extracción.

¡Disfruta del proyecto!
