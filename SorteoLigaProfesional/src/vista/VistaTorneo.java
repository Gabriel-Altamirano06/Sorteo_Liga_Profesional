
package vista;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Partido;

public class VistaTorneo {

    private Scanner teclado;

    // Constructor
    public VistaTorneo() {
        teclado = new Scanner(System.in);
    }

    // Mostrar el menu principal
    public int mostrarMenu() {

        System.out.println("\n====================================");
        System.out.println("       SORTEO DE LIGA PROFESIONAL");
        System.out.println("====================================");
        System.out.println("1. Realizar sorteo de octavos");
        System.out.println("2. Registrar ganadores y sortear cuartos");
        System.out.println("3. Registrar ganadores y sortear semifinales");
        System.out.println("4. Registrar ganadores y sortear la final");
        System.out.println("5. Mostrar todos los resultados");
        System.out.println("6. Guardar torneo");
        System.out.println("7. Cargar torneo");
        System.out.println("0. Salir");
        System.out.println("====================================");
        System.out.print("Seleccione una opcion: ");

        String entrada = teclado.nextLine();

        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Mostrar un mensaje al usuario
    public void mostrarMensaje(String mensaje) {

        System.out.println("\n" + mensaje);
    }

    // Mostrar los partidos de una etapa
    public void mostrarPartidos(
            String etapa,
            ArrayList<Partido> partidos) {

        System.out.println("\n====================================");
        System.out.println("       " + etapa.toUpperCase());
        System.out.println("====================================");

        if (partidos == null || partidos.isEmpty()) {

            System.out.println("Todavia no existen partidos registrados.");
            return;
        }

        for (int i = 0; i < partidos.size(); i++) {

            System.out.println(
                (i + 1) + ". " + partidos.get(i).toString()
            );
        }

        System.out.println("====================================");
    }

    // Solicitar al usuario el ganador de un partido
    public int solicitarGanador(Partido partido) {

        int opcion = -1;

        while (opcion != 1 && opcion != 2) {

            System.out.println("\nSeleccione el ganador del partido:");

            System.out.println(
                "1. " + partido.getEquipo1().getNombre()
            );

            System.out.println(
                "2. " + partido.getEquipo2().getNombre()
            );

            System.out.print("Ingrese su opcion: ");

            String entrada = teclado.nextLine();

            try {

                opcion = Integer.parseInt(entrada.trim());

                if (opcion != 1 && opcion != 2) {
                    System.out.println(
                        "Opcion incorrecta. Seleccione 1 o 2."
                    );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                    "Debe ingresar un numero valido."
                );

                opcion = -1;
            }
        }

        return opcion;
    }

    // Solicitar el nombre del archivo
    public String solicitarNombreArchivo() {

        System.out.print(
            "Ingrese el nombre del archivo (por defecto torneo.ser): "
        );

        String nombre = teclado.nextLine().trim();

        if (nombre.isEmpty()) {
            nombre = "torneo.ser";
        }

        return nombre;
    }
}
