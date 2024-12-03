
public class Carrera {
    private final String[] posiciones;
    private final int longitudCarrera;
    private boolean carreraTerminada;

    public Carrera(int numCompetidores, int longitudCarrera) {
        this.posiciones = new String[numCompetidores];
        this.longitudCarrera = longitudCarrera;
        this.carreraTerminada = false;

        for (int i = 0; i < numCompetidores; i++) {
            posiciones[i] = "";
        }
    }

    public synchronized boolean actualizarPosicion(int id, String competidor, int avance) {
        if (carreraTerminada) return true;

        posiciones[id] += "-".repeat(avance);
        System.out.println(competidor + ": " + posiciones[id] + ">");

        if (posiciones[id].length() >= longitudCarrera) {
            System.out.println("¡" + competidor + " ha ganado!");
            carreraTerminada = true;
        }

        return carreraTerminada;
    }

    public boolean esCarreraTerminada() {
        return carreraTerminada;
    }

    public static void main(String[] args) {
        int numCompetidores = 6;
        int longitudCarrera = 50;

        Carrera carrera = new Carrera(numCompetidores, longitudCarrera);
        Thread[] competidores = new Thread[numCompetidores];

        for (int i = 0; i < numCompetidores / 2; i++) {
            competidores[i] = new Thread(new Tortuga(carrera, i));
            competidores[i + numCompetidores / 2] = new Thread(new Conejo(carrera, i + numCompetidores / 2));
        }

        for (Thread competidor : competidores) {
            competidor.start();
        }

        for (Thread competidor : competidores) {
            try {
                competidor.join();
            } catch (InterruptedException e) {}
        }
    }
}
