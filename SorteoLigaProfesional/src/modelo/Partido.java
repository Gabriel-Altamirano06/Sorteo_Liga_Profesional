
package modelo;

import java.io.Serializable;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    // Equipos que participan en el partido
    private Equipo equipo1;
    private Equipo equipo2;

    // Etapa del torneo
    private String etapa;

    // Equipo ganador
    private Equipo ganador;


    public Partido(Equipo equipo1, Equipo equipo2, String etapa) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.etapa = etapa;
        this.ganador = null;
    }

    // Obtener el primer equipo
    public Equipo getEquipo1() {
        return equipo1;
    }

    // Obtener el segundo equipo
    public Equipo getEquipo2() {
        return equipo2;
    }

    // Obtener la etapa
    public String getEtapa() {
        return etapa;
    }

    // Obtener el ganador
    public Equipo getGanador() {
        return ganador;
    }

    // Registrar el ganador del partido
    public void setGanador(Equipo ganador) {

        if (ganador != equipo1 && ganador != equipo2) {
            throw new IllegalArgumentException(
                "El ganador debe ser uno de los equipos participantes."
            );
        }

        this.ganador = ganador;
    }

    // Mostrar información del partido
    @Override
    public String toString() {

        String resultado = equipo1.getNombre()
                + " vs " + equipo2.getNombre();

        if (ganador != null) {
            resultado += " | Ganador: " + ganador.getNombre();
        }

        return resultado;
    }
}
