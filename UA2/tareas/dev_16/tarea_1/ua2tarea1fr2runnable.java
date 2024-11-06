public class ua2tarea1fr2runnable implements Runnable {
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
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new ua2tarea1fr2runnable());
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al unir el hilo: " + e.getMessage());
            }
        }

        System.out.println("Valor final del contador (con sincronizacion en Runnable): " + contador);
    }
}