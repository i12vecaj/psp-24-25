public class ua2tarea1fr2 extends Thread {
    private static int contador = 0;

    public static synchronized void incrementar() {
        for (int i = 0; i < 1000; i++) {
            contador++;
        }
    }

    @Override
    public void run() {
        incrementar();
    }

    public static void main(String[] args) {
        ua2tarea1fr2[] hilos = new ua2tarea1fr2[5];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new ua2tarea1fr2();
            hilos[i].start();
        }

        for (ua2tarea1fr2 hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al unir el hilo: " + e.getMessage());
            }
        }

        System.out.println("Valor final del contador (con sincronización en Thread): " + contador);
    }
}