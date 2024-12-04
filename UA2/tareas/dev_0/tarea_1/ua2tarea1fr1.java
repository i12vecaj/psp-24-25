public class Ua2Tarea1FR1 {
    // Contador de los hilos (debe ser seguro para hilos)
    private static int contador = 0;

    // Bloqueo para sincronizar el acceso al contador
    private static final Object lock = new Object();

    public static void main(String[] args) {
        // Crear 5 hilos
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // Incrementar el contador de forma sincronizada
                    synchronized (lock) {
                        contador++;
                    }
                }
            });
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen su ejecución
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                System.err.println("El hilo fue interrumpido: " + e.getMessage());
            }
        }

        System.out.println("El valor final de contador es: " + contador);
    }
}
