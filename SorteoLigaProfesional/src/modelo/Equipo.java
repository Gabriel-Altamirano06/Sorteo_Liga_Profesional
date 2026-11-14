
package modelo;

import java.io.Serializable;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos del equipo
    private String nombre;

    // Constructor
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Nombre del equipo
    public String getNombre() {
        return nombre;
    }

    // Modificar el nombre del equipo
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Mostrar información del equipo
    @Override
    public String toString() {
        return nombre;
    }
}
