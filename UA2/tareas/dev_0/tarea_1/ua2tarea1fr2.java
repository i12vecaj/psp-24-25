public class ua2tarea1fr2 {

    // Variable compartida entre los hilos
    private static int contador = 0;

//este metodo nos permite incrementar usando el synchronized 
    public synchronized static void incrementarContador() {
        contador += 1000;
    }

    public static void main(String[] args) {
        
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                incrementarContador();
            }
        };

        //5hilos 
        Thread[] hil0 = new Thread[5];

        try {
            for (int i = 0; i < 5; i++) {
                hil0[i] = new Thread(tarea);
                hil0[i].start();  
            }
            
            for (int i = 0; i < 5; i++) {
                hil0[i].join();
            }

            
            System.out.println("Valor final del contador: " + contador);

        } catch (InterruptedException e) {
            // Control básico de errores
            System.err.println("Error al ejecutar los hilos: " + e.getMessage());
        }
    }
}
