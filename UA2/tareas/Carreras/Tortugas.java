import java.util.Random;

class Tortuga implements Runnable {
    private final Carrera carrera;
    private final int id;
    private final String nombre;
    private static final Random random = new Random();

    public Tortuga(Carrera carrera, int id) {
        this.carrera = carrera;
        this.id = id;
        this.nombre = "Tortuga " + id;
    }

    @Override
    public void run() {
        while (!carrera.esCarreraTerminada()) {
            int avance = random.nextInt(4) + 1;
            boolean terminada = carrera.actualizarPosicion(id, nombre, avance);

            if (terminada) break;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}
