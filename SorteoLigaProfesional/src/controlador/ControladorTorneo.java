
package controlador;

import java.io.IOException;
import java.util.ArrayList;

import modelo.Equipo;
import modelo.Partido;
import modelo.Torneo;

import vista.VistaTorneo;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

public class ControladorTorneo {

    private Torneo torneo;
    private VistaTorneo vista;

    // Constructor
    public ControladorTorneo(
            Torneo torneo,
            VistaTorneo vista) {

        this.torneo = torneo;
        this.vista = vista;
    }

    // Iniciar la aplicacion
    public void iniciar() {

        boolean continuar = true;

        while (continuar) {

            int opcion = vista.mostrarMenu();

            try {

                switch (opcion) {

                    case 1:
                        realizarOctavos();
                        break;

                    case 2:
                        realizarCuartos();
                        break;

                    case 3:
                        realizarSemifinales();
                        break;

                    case 4:
                        realizarFinal();
                        break;

                    case 5:
                        mostrarResultados();
                        break;

                    case 6:
                        guardarTorneo();
                        break;

                    case 7:
                        cargarTorneo();
                        break;

                    case 0:
                        vista.mostrarMensaje(
                            "Gracias por utilizar el sistema."
                        );

                        continuar = false;
                        break;

                    default:
                        vista.mostrarMensaje(
                            "Opcion incorrecta. Intente nuevamente."
                        );
                        break;
                }

            } catch (NumeroEquiposInvalidoException e) {

                vista.mostrarMensaje(
                    "ERROR: " + e.getMessage()
                );

            } catch (SorteoInvalidoException e) {

                vista.mostrarMensaje(
                    "ERROR: " + e.getMessage()
                );

            } catch (IOException e) {

                vista.mostrarMensaje(
                    "ERROR DE ARCHIVO: " + e.getMessage()
                );

            } catch (ClassNotFoundException e) {

                vista.mostrarMensaje(
                    "ERROR: No se pudo reconocer el objeto guardado."
                );
            }
        }
    }

    // Realizar octavos de final
    private void realizarOctavos()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        torneo.sortearOctavos();

        vista.mostrarMensaje(
            "Sorteo de octavos realizado correctamente."
        );

        vista.mostrarPartidos(
            "Octavos de final",
            torneo.getPartidosOctavos()
        );
    }

    // Registrar ganadores de octavos y realizar cuartos
    private void realizarCuartos()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        registrarGanadores(torneo.getPartidosOctavos());

        torneo.sortearCuartos();

        vista.mostrarMensaje(
            "Sorteo de cuartos realizado correctamente."
        );

        vista.mostrarPartidos(
            "Cuartos de final",
            torneo.getPartidosCuartos()
        );
    }

    // Registrar ganadores de cuartos y realizar semifinales
    private void realizarSemifinales()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        registrarGanadores(torneo.getPartidosCuartos());

        torneo.sortearSemifinales();

        vista.mostrarMensaje(
            "Sorteo de semifinales realizado correctamente."
        );

        vista.mostrarPartidos(
            "Semifinales",
            torneo.getPartidosSemifinales()
        );
    }

    // Registrar ganadores de semifinales y realizar la final
    private void realizarFinal()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        registrarGanadores(torneo.getPartidosSemifinales());

        torneo.sortearFinal();

        vista.mostrarMensaje(
            "Sorteo de la final realizado correctamente."
        );

        vista.mostrarPartidos(
            "FINAL",
            torneo.getPartidosFinal()
        );
    }

    // Registrar los ganadores de los partidos
    private void registrarGanadores(
            ArrayList<Partido> partidos)
            throws SorteoInvalidoException {

        if (partidos == null || partidos.isEmpty()) {

            throw new SorteoInvalidoException(
                "Primero debe realizar el sorteo de la etapa anterior."
            );
        }

        for (Partido partido : partidos) {

            int opcion = vista.solicitarGanador(partido);

            Equipo ganador;

            if (opcion == 1) {

                ganador = partido.getEquipo1();

            } else {

                ganador = partido.getEquipo2();
            }

            torneo.registrarGanador(partido, ganador);
        }
    }

    // Mostrar los resultados de todas las etapas
    private void mostrarResultados() {

        vista.mostrarPartidos(
            "Octavos de final",
            torneo.getPartidosOctavos()
        );

        vista.mostrarPartidos(
            "Cuartos de final",
            torneo.getPartidosCuartos()
        );

        vista.mostrarPartidos(
            "Semifinales",
            torneo.getPartidosSemifinales()
        );

        vista.mostrarPartidos(
            "Final",
            torneo.getPartidosFinal()
        );
    }

    // Guardar el torneo
    private void guardarTorneo() throws IOException {

        String nombreArchivo =
                vista.solicitarNombreArchivo();

        torneo.guardarTorneo(nombreArchivo);

        vista.mostrarMensaje(
            "Torneo guardado correctamente en: "
            + nombreArchivo
        );
    }

    // Cargar un torneo guardado
    private void cargarTorneo()
            throws IOException, ClassNotFoundException {

        String nombreArchivo =
                vista.solicitarNombreArchivo();

        torneo = Torneo.cargarTorneo(nombreArchivo);

        vista.mostrarMensaje(
            "Torneo recuperado correctamente desde: "
            + nombreArchivo
        );

        mostrarResultados();
    }
}
