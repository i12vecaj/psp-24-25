import java.util.Random;

class Conejo implements Runnable {
    private final Carrera carrera;
    private final int id;
    private final String nombre;
    private static final Random random = new Random();

    public Conejo(Carrera carrera, int id) {
        this.carrera = carrera;
        this.id = id;
        this.nombre = "Conejo " + id;
    }

    @Override
    public void run() {
        while (!carrera.esCarreraTerminada()) {
            int avance = random.nextBoolean() ? random.nextInt(4) + 1 : 0;
            boolean terminada = carrera.actualizarPosicion(id, nombre, avance);

            if (terminada) break;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
}
