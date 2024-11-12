public class ua2tarea1fr2runnable implements Runnable {
    private static int contador = 0; // Contador compartido

    // Método sincronizado para incrementar el contador
    public static synchronized void incrementarContador() {
        contador++;
    }

    // Método para obtener el valor final del contador
    public static int getContador() {
        return contador;
    }

    // Tarea de cada hilo: incrementa el contador 1000 veces
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementarContador();
        }
    }

    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];
        Runnable tarea = new ua2tarea1fr2runnable();

        // Crea e inicia 5 hilos con la misma tarea
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(tarea);
            hilos[i].start();
        }

        // Espera a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try { hilo.join(); } catch (InterruptedException e) {}
        }

        // Muestra el resultado final 
        System.out.println("Contador final con sincronización (Runnable): " + getContador());
    }
}
