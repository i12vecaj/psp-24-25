public class ua2tarea1fr1 {
    //contador de los hilos
    static int contador = 0;

    public static void main(String[] args) {
        //5hilos
        Thread[]hil0= new Thread[5];

       //hilos para incremnetar el contador
        for (int i = 0; i < 5; i++) {
            hil0[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        contador++;
                    }
                }
            });
            hil0[i].start();
        }

        // Esperar a que todos los hilos terminen su ejecución
        try {
            for (int i = 0; i < 5; i++) {
                hil0[i].join();
            }
        } catch (InterruptedException e) {
        }


        System.out.println("El valor final de contador es: " + contador);
    }
}
