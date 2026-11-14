
package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    // Lista de equipos participantes
    private ArrayList<Equipo> equipos;

    // Partidos de cada etapa
    private ArrayList<Partido> partidosOctavos;
    private ArrayList<Partido> partidosCuartos;
    private ArrayList<Partido> partidosSemifinales;
    private ArrayList<Partido> partidosFinal;

    // Constructor
    public Torneo() {

        equipos = new ArrayList<Equipo>();

        partidosOctavos = new ArrayList<Partido>();
        partidosCuartos = new ArrayList<Partido>();
        partidosSemifinales = new ArrayList<Partido>();
        partidosFinal = new ArrayList<Partido>();

        inicializarEquipos();
    }

    // Crear los 16 equipos iniciales
    private void inicializarEquipos() {

        equipos.add(new Equipo("FC Barcelona"));
        equipos.add(new Equipo("Manchester City"));
        equipos.add(new Equipo("Real Madrid"));
        equipos.add(new Equipo("Arsenal"));
        equipos.add(new Equipo("Atletico Madrid"));
        equipos.add(new Equipo("Liverpool FC"));
        equipos.add(new Equipo("Paris Saint-German"));
        equipos.add(new Equipo("Galatasaray"));
        equipos.add(new Equipo("Bayern Munich"));
        equipos.add(new Equipo("Borussia Dortmund"));
        equipos.add(new Equipo("Napoli"));
        equipos.add(new Equipo("Inter"));
        equipos.add(new Equipo("Real Betis"));
        equipos.add(new Equipo("Chelsea"));
        equipos.add(new Equipo("Aston Villa"));
        equipos.add(new Equipo("Juventus"));
    }

    // Obtener los equipos
    public ArrayList<Equipo> getEquipos() {
        return new ArrayList<Equipo>(equipos);
    }

    // Obtener partidos de octavos
    public ArrayList<Partido> getPartidosOctavos() {
        return new ArrayList<Partido>(partidosOctavos);
    }

    // Obtener partidos de cuartos
    public ArrayList<Partido> getPartidosCuartos() {
        return new ArrayList<Partido>(partidosCuartos);
    }

    // Obtener partidos de semifinales
    public ArrayList<Partido> getPartidosSemifinales() {
        return new ArrayList<Partido>(partidosSemifinales);
    }

    // Obtener partidos de la final
    public ArrayList<Partido> getPartidosFinal() {
        return new ArrayList<Partido>(partidosFinal);
    }

    // Validar que no existan equipos repetidos
    private void validarEquipos(ArrayList<Equipo> lista)
            throws SorteoInvalidoException {

        Set<String> nombres = new HashSet<String>();

        for (Equipo equipo : lista) {

            if (equipo == null) {
                throw new SorteoInvalidoException(
                    "Existe un equipo nulo en la lista."
                );
            }

            String nombre = equipo.getNombre();

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new SorteoInvalidoException(
                    "Existe un equipo sin nombre."
                );
            }

            String nombreNormalizado =
                    nombre.trim().toLowerCase();

            if (!nombres.add(nombreNormalizado)) {
                throw new SorteoInvalidoException(
                    "El equipo " + nombre + " esta repetido."
                );
            }
        }
    }

    // Metodo principal para sortear los partidos
    public ArrayList<Partido> sortearPartidos(
            ArrayList<Equipo> equiposEtapa,
            String etapa)
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        if (equiposEtapa == null) {
            throw new NumeroEquiposInvalidoException(
                "La lista de equipos no puede ser nula."
            );
        }

        int cantidad = equiposEtapa.size();

        // Comprobar que sea una potencia de 2
        if (cantidad < 2 ||
                (cantidad & (cantidad - 1)) != 0) {

            throw new NumeroEquiposInvalidoException(
                "Cantidad de equipos invalida: " + cantidad
                + ". Debe ser 2, 4, 8, 16, etc."
            );
        }

        if (etapa == null || etapa.trim().isEmpty()) {
            throw new SorteoInvalidoException(
                "La etapa no puede estar vacia."
            );
        }

        // Validar equipos
        validarEquipos(equiposEtapa);

        // Copia de los equipos disponibles
        ArrayList<Equipo> disponibles =
                new ArrayList<Equipo>(equiposEtapa);

        // Lista de partidos resultantes
        ArrayList<Partido> resultado =
                new ArrayList<Partido>();

        Random aleatorio = new Random();

        // Ejecutar el algoritmo recursivo
        sorteoRecursivo(
            disponibles,
            etapa,
            resultado,
            aleatorio
        );

        return resultado;
    }

    // ALGORITMO RECURSIVO DE EMPAREJAMIENTO
    private void sorteoRecursivo(
            ArrayList<Equipo> disponibles,
            String etapa,
            ArrayList<Partido> partidos,
            Random aleatorio)
            throws SorteoInvalidoException {

        // Caso base
        if (disponibles.isEmpty()) {
            return;
        }

        // Verificar que existan equipos suficientes
        if (disponibles.size() < 2 ||
                disponibles.size() % 2 != 0) {

            throw new SorteoInvalidoException(
                "No es posible completar el sorteo."
            );
        }

        // Seleccionar primer equipo aleatoriamente
        int indice1 =
                aleatorio.nextInt(disponibles.size());

        Equipo equipo1 =
                disponibles.remove(indice1);

        // Seleccionar segundo equipo aleatoriamente
        int indice2 =
                aleatorio.nextInt(disponibles.size());

        Equipo equipo2 =
                disponibles.remove(indice2);

        // Evitar enfrentamientos del mismo equipo
        if (equipo1 == equipo2 ||
                equipo1.getNombre().equalsIgnoreCase(
                    equipo2.getNombre())) {

            throw new SorteoInvalidoException(
                "Un equipo no puede enfrentarse a si mismo."
            );
        }

        // Crear el partido
        Partido nuevoPartido =
                new Partido(equipo1, equipo2, etapa);

        partidos.add(nuevoPartido);

        // LLAMADA RECURSIVA
        sorteoRecursivo(
            disponibles,
            etapa,
            partidos,
            aleatorio
        );
    }

    // Sortear octavos de final
    public void sortearOctavos()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        partidosOctavos = sortearPartidos(
            equipos,
            "Octavos de final"
        );

        // Limpiar las etapas posteriores
        partidosCuartos.clear();
        partidosSemifinales.clear();
        partidosFinal.clear();
    }

    // Obtener ganadores de una etapa
    private ArrayList<Equipo> obtenerGanadores(
            ArrayList<Partido> partidos,
            String etapa)
            throws SorteoInvalidoException {

        if (partidos == null || partidos.isEmpty()) {
            throw new SorteoInvalidoException(
                "Primero debe realizarse el sorteo de "
                + etapa + "."
            );
        }

        ArrayList<Equipo> ganadores =
                new ArrayList<Equipo>();

        for (Partido partido : partidos) {

            if (partido == null ||
                    partido.getGanador() == null) {

                throw new SorteoInvalidoException(
                    "Todos los partidos de " + etapa
                    + " deben tener un ganador."
                );
            }

            ganadores.add(partido.getGanador());
        }

        validarEquipos(ganadores);

        return ganadores;
    }

    // Sortear cuartos de final
    public void sortearCuartos()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        ArrayList<Equipo> ganadores =
                obtenerGanadores(
                    partidosOctavos,
                    "octavos"
                );

        partidosCuartos = sortearPartidos(
            ganadores,
            "Cuartos de final"
        );

        partidosSemifinales.clear();
        partidosFinal.clear();
    }

    // Sortear semifinales
    public void sortearSemifinales()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        ArrayList<Equipo> ganadores =
                obtenerGanadores(
                    partidosCuartos,
                    "cuartos"
                );

        partidosSemifinales = sortearPartidos(
            ganadores,
            "Semifinales"
        );

        partidosFinal.clear();
    }

    // Sortear la final
    public void sortearFinal()
            throws NumeroEquiposInvalidoException,
                   SorteoInvalidoException {

        ArrayList<Equipo> ganadores =
                obtenerGanadores(
                    partidosSemifinales,
                    "semifinales"
                );

        partidosFinal = sortearPartidos(
            ganadores,
            "Final"
        );
    }

    // Verificar que el partido pertenezca al torneo
    private boolean partidoRegistrado(Partido partido) {

        return partidosOctavos.contains(partido)
            || partidosCuartos.contains(partido)
            || partidosSemifinales.contains(partido)
            || partidosFinal.contains(partido);
    }

    // Registrar ganador de un partido
    public void registrarGanador(
            Partido partido,
            Equipo ganador)
            throws SorteoInvalidoException {

        if (partido == null ||
                !partidoRegistrado(partido)) {

            throw new SorteoInvalidoException(
                "El partido no pertenece a este torneo."
            );
        }

        try {

            partido.setGanador(ganador);

        } catch (IllegalArgumentException e) {

            throw new SorteoInvalidoException(
                "Error al registrar el ganador: "
                + e.getMessage()
            );
        }
    }

    // Guardar torneo
    public void guardarTorneo(String nombreArchivo)
            throws IOException {

        try (ObjectOutputStream salida =
                new ObjectOutputStream(
                    new FileOutputStream(nombreArchivo))) {

            salida.writeObject(this);
        }
    }

    // Cargar torneo
    public static Torneo cargarTorneo(String nombreArchivo)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream entrada =
                new ObjectInputStream(
                    new FileInputStream(nombreArchivo))) {

            Object objeto = entrada.readObject();

            if (!(objeto instanceof Torneo)) {

                throw new IOException(
                    "El archivo no contiene un torneo valido."
                );
            }

            return (Torneo) objeto;
        }
    }
}
