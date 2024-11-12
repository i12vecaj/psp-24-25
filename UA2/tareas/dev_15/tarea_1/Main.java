public class Main {
    private static int contador = 0; // Contador compartido entre los hilos

    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];

        // Crea e inicia 5 hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    contador++; // Incremento sin sincronización
                }
            });
            hilos[i].start();
        }

        // Espera a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try { hilo.join(); } catch (InterruptedException e) {}
        }

        // Muestra el resultado final 
        System.out.println("Contador final sin sincronización: " + contador);
    }
}
