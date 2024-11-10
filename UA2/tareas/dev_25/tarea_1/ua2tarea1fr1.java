public class ua2tarea1fr1 {
    // Variable global
    private static int contador = 0;

    public static void main(String[] args) {
        // Creamos 5 hilos
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    // Cada hilo incrementará el contador 1000 veces
                    for (int j = 0; j < 1000; j++) {
                        contador++; // Incremento no sincronizado
                    }
                }
            });
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join(); // Asegura que todos los hilos terminen antes de continuar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el resultado final
        System.out.println("Valor final: " + contador);
    }
}
