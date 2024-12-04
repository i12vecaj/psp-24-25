public class Ua2Tarea1FR2 {

    // Variable compartida entre los hilos
    private static int contador = 0;

    // Método sincronizado para incrementar el contador
    private synchronized static void incrementarContador() {
        contador += 1000;
    }

    public static void main(String[] args) {
        Runnable tarea = () -> incrementarContador();

        // Crear 5 hilos
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(tarea);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                System.err.println("Error al ejecutar los hilos: " + e.getMessage());
            }
        }

        System.out.println("Valor final del contador: " + contador);
    }
}
