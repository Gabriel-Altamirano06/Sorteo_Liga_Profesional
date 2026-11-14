# Sorteo de Liga Profesional

Aplicación desarrollada en Java para realizar sorteos de enfrentamientos de una liga profesional de fútbol, utilizando programación orientada a objetos, arquitectura Modelo-Vista-Controlador (MVC), recursividad, excepciones personalizadas y serialización de objetos.

## Descripción del proyecto

El proyecto consiste en desarrollar un sistema de sorteo para una liga profesional conformada inicialmente por 16 equipos. La aplicación organiza aleatoriamente los enfrentamientos de un torneo de eliminación directa, permitiendo avanzar desde octavos de final hasta la final.

El programa funciona mediante una interfaz de consola que permite realizar los sorteos, seleccionar los ganadores de las etapas anteriores, visualizar los resultados y guardar o recuperar la información del torneo.

## Objetivo

Desarrollar una aplicación en Java que permita gestionar los sorteos de una liga profesional, aplicando los conceptos de programación orientada a objetos correspondientes a la Unidad 3: Manejo de Excepciones y Archivos.

## Arquitectura del proyecto (MVC)

El proyecto está organizado mediante los siguientes paquetes:

| Paquete | Clases | Responsabilidad |
|---|---|---|
| `modelo` | Equipo, Partido, Torneo | Administración de equipos, partidos, sorteo y persistencia. |
| `vista` | VistaTorneo | Presentación de información y entrada de datos por consola. |
| `controlador` | ControladorTorneo | Coordina la interacción entre el modelo y la vista. |
| `excepciones` | NumeroEquiposInvalidoException, SorteoInvalidoException | Manejo de errores específicos del sistema. |
| `aplicacion` | Main | Punto de entrada y ejecución de la aplicación. |

## Etapas del torneo

La aplicación contempla cuatro etapas de eliminación directa:

| Etapa | Equipos | Partidos |
|---|---:|---:|
| Octavos de final | 16 | 8 |
| Cuartos de final | 8 | 4 |
| Semifinales | 4 | 2 |
| Final | 2 | 1 |

Los ganadores de cada etapa avanzan a la siguiente ronda hasta llegar al enfrentamiento final.

## Excepciones personalizadas

Se implementaron dos excepciones propias que extienden la clase `Exception`:

**1. NumeroEquiposInvalidoException**

Se utiliza cuando la cantidad de equipos no cumple las condiciones necesarias para realizar un torneo de eliminación directa.

**2. SorteoInvalidoException**

Se utiliza para controlar situaciones inválidas durante el proceso de emparejamiento y la gestión del torneo.

Las excepciones se manejan mediante bloques `try-catch` para evitar interrupciones inesperadas de la aplicación.

## 💾 Serialización y archivos

El sistema implementa persistencia mediante la interfaz `Serializable` y las siguientes clases de Java:

- `ObjectOutputStream`
- `FileOutputStream`
- `ObjectInputStream`
- `FileInputStream`

El torneo puede guardarse en un archivo binario, por ejemplo `torneo.ser`, y recuperarse posteriormente conservando los equipos, enfrentamientos y ganadores registrados.

## Instrucciones para ejecutar el proyecto

1. Descargar o clonar este repositorio de GitHub.
2. Abrir Eclipse IDE.
3. Seleccionar `File` → `Import`.
4. Elegir `General` → `Existing Projects into Workspace`.
5. Seleccionar como directorio raíz la carpeta `SorteoLigaProfesional` que se encuentra dentro del repositorio descargado.
6. Finalizar la importación del proyecto.
7. Abrir el paquete `aplicacion`.
8. Localizar el archivo `Main.java`.
9. Hacer clic derecho y seleccionar `Run As` → `Java Application`.

## Opciones del menú principal

Al ejecutar la aplicación, se presenta un menú por consola con las siguientes opciones:

| Opción | Descripción |
|---|---|
| 1 | Realizar sorteo de octavos de final. |
| 2 | Registrar ganadores de octavos y sortear cuartos. |
| 3 | Registrar ganadores de cuartos y sortear semifinales. |
| 4 | Registrar ganadores de semifinales y sortear la final. |
| 5 | Mostrar los resultados de todas las etapas. |
| 6 | Guardar el torneo. |
| 7 | Cargar un torneo previamente guardado. |
| 0 | Salir de la aplicación. |


