public class ua2tarea1fr1 extends Thread {
    private static int contador = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador++;
        }
    }

    public static void main(String[] args) {
        ua2tarea1fr1[] hilos = new ua2tarea1fr1[5];

        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new ua2tarea1fr1();
            hilos[i].start();
        }

        for (ua2tarea1fr1 hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al unir el hilo: " + e.getMessage());
            }
        }

        System.out.println("Valor final del contador (sin sincronización): " + contador);
    }
}