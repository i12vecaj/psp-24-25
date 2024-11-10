public class ua2tarea1fr2runnable {
    // Variable global
    private static int contador = 0;

    public static void main(String[] args) {
        // Crear 5 hilos mediante la interfaz Runnable
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                // Sincronizar el acceso a la variable compartida
                for (int j = 0; j < 1000; j++) {
                    synchronized (ua2tarea1fr2runnable.class) { // Sincronización de acceso
                        contador++;
                    }
                }
            }
        };

        // Lanzar los hilos
        Thread[] hilos = new Thread[5];
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(tarea);
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
        System.out.println("Valor final del contador (con Runnable): " + contador);
    }
}
