# Plataforma de Streaming — Entregable 2

Proyecto académico de la materia **Taller de Lenguajes II**, correspondiente al cuarto semestre de la carrera **Ingeniería en Computación** de las **Facultades de Ingeniería e Informática** de la **Universidad Nacional de La Plata**.

Esta entrega corresponde a la persistencia de objetos en base de datos, implementando una prueba de concepto (POC) que permite almacenar información de la aplicación en una base de datos SQLite.  
Se trabajó sobre el modelo de clases del entregable 1, extendiéndolo con nuevas clases y responsabilidades relacionadas con la gestión de reseñas de películas, aplicando el patrón DAO (Data Access Object) y los mecanismos de ordenación vistos en la teoría.

## Aclaraciones

- Debido a que nuestro modelo de clases difería con el modelo que se usó de ejemplo en el enunciado del entregable, debimos modificar ciertos aspectos. El principal cambio fue asociar los datos personales del enunciado con nuestra clase Profile, y los usuarios del enunciado con nuestra clase Account. Estas difieren en ciertos atributos, pero pudimos adaptarlas para cumplir el enunciado (por ejemplo, en vez de verificar que el DNI de una persona sea único, ahora verificamos que el email de Account sea único).

- Además, debimos modificar ciertas clases de nuestro modelo de clases del entregable 1 para poder implementar las acciones de la plataforma, en particular la clase Profile, la cual ahora posee una referencia a una Account en lugar de que la clase Account contenga referencias a sus perfiles. Esto se debe a que, según el enunciado, al registrar un usuario (Account) debía elegirse a qué persona se asociaba (Profile), indicando que los perfiles son independientes de las cuentas.

## Contenido del repositorio

- /src/ → código fuente del proyecto (organizado por paquetes: model, dao, services)
- /bin/ → archivos compilados generados automáticamente.
- /lib/ → librería sqlite-jdbc necesaria para la conexión con la base de datos.
- /data/ → carpeta donde se almacena la base de datos SQLite con las tablas creadas automáticamente en la primera ejecución.
- Entregable2.jar → archivo ejecutable de la aplicación.
- README.md → este documento descriptivo.

Fay Dylan Gaspar y Giovagnoli Franco Rubén | Grupo 8 | 27/10/2025
