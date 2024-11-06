public class ua2tarea1fr1 {
    //variavle que se compartira por los hilos
    private static int contador = 0;

    public static void main(String[] args) {
        //5hilos
        Thread[]hilos= new Thread[5];

       //hilos para incremnetar el contador
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        contador++;
                    }
                }
            });
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen su ejecución
        try {
            for (int i = 0; i < 5; i++) {
                hilos[i].join();
            }
        } catch (InterruptedException e) {
        }


        System.out.println("El valor final de contador es: " + contador);
    }
}
