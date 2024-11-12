public class ua2tarea1fr2runnable {
    private static int contador = 0;

    public static void main(String[] args) {
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                incrementar();
            }
        };

        Thread[] hilos = new Thread[5];

        // Creación de 5 hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(tarea);
        }

        // Inicio de los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Espera a que terminen los hilos
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error en la ejecución del hilo: " + e.getMessage());
            }
        }

        // Resultado final
        System.out.println("Valor final de contador (Runnable con sincronización): " + contador);
    }

    // Método sincronizado para incrementar el contador
    private static synchronized void incrementar() {
        for (int j = 0; j < 1000; j++) {
            contador++;
        }
    }
}
