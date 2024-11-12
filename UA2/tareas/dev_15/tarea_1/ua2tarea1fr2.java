public class ua2tarea1fr2 {
    private static int contador = 0; // Contador compartido

    // Método sincronizado para incrementar el contador
    public static synchronized void incrementarContador() {
        contador++;
    }

    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];

        // Crea e inicia 5 hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementarContador(); // Incremento sincronizado
                }
            });
            hilos[i].start();
        }

        // Espera a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try { hilo.join(); } catch (InterruptedException e) {}
        }

        // Muestra el resultado final
        System.out.println("Contador final con sincronización (Thread): " + contador);
    }
}
