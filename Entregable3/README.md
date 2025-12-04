# Plataforma de Streaming — Entregable 3

Proyecto académico de la materia **Taller de Lenguajes II**, correspondiente al cuarto semestre de la carrera **Ingeniería en Computación** de las **Facultades de Ingeniería e Informática** de la **Universidad Nacional de La Plata**.

## En qué se basa esta entrega

Este tercer entregable corresponde a una nueva iteración del desarrollo de la Plataforma de Streaming. En esta etapa se amplía el alcance de la aplicación incorporando interfaz gráfica, manejo de excepciones, lectura de archivos y concurrencia.

Las principales funcionalidades implementadas son:

- **Interfaz Gráfica de Usuario (GUI)** para todas las pantallas de la aplicación.
- **Validaciones y manejo de excepciones**, incluyendo excepciones personalizadas definidas por el equipo.
- **Importación de datos desde un archivo CSV**, cargando información de películas en la base de datos y en memoria.
- **Procesamiento concurrente** para mostrar una pantalla de carga mientras se realiza la importación de datos.
- **Búsqueda de información de películas** mediante un servicio externo (OMDb API).

El proyecto mantiene la estructura basada en el patrón **MVC**, garantizando la separación entre lógica, interfaz y persistencia, y asegurando un flujo de uso completo de “punta a punta”.

## Aclaraciones
### ✓ Cambio en la Base de Datos
Para el correcto funcionamiento de la aplicación fue necesario adaptar la estructura de algunas tablas, y en base a esto ajustar los DAOs. En particular, se incorporó el campo **`NEVER_LOG_IN`** en la tabla **Profile**, cuyo propósito es registrar si un perfil accede por primera vez a la plataforma. Este dato sirve para determinar qué conjunto de películas mostrar al usuario en su primer ingreso.

### ✓ Agregado de perfiles
Con el fin de respetar el modelo definido en entregas anteriores, se implementó la posibilidad de que cada cuenta posea múltiples perfiles. Al iniciar sesión, el usuario puede seleccionar con cuál de esos perfiles desea acceder, manteniendo la coherencia con el diseño planteado en las etapas previas del proyecto.

### ✓ Consideración sobre accesos sucesivos de un usuario
La aplicación distingue entre el primer acceso de un perfil y sus ingresos posteriores.  
- **Primer acceso:** se muestran las 10 películas con mejor valoración promedio.  
- **Accesos sucesivos:** se presentan 10 películas seleccionadas aleatoriamente entre aquellas que el perfil aún no ha calificado.



## Contenido del repositorio

- CodigoFuente.zip → Archivo comprimido que contiene el proyecto Java de la app.
- /data → carpeta donde se almacena la base de datos SQLite con las tablas creadas automáticamente en la primera ejecución, y el archivo CSV que contiene las peliculas.
- Entregable3.jar → archivo ejecutable de la aplicación.
- README.md → este documento descriptivo.

Fay Dylan Gaspar y Giovagnoli Franco Rubén | Grupo 8 | 04/12/2025
