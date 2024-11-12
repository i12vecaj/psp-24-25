public class ua2tarea1fr1{
    
    private static int contador = 0;
    public static void main(String[] args) {
      
        Thread hilo1 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("soy el hilo1: " + contador);
            }
            
        });

        hilo1.start();

        Thread hilo2 = new Thread(() -> {
             for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("soy el hilo2: " + contador);
            }
           
        });
        hilo2.start();

        Thread hilo3 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("soy el hilo3: " + contador);
            }
          
        });
        hilo3.start();

        Thread hilo4 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("soy el hilo4: " + contador);
            }
            
        });
        hilo4.start();

        Thread hilo5 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                 System.out.println("soy el hilo5: " + contador);
            }
          
        });
        hilo5.start();

    
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido: " + e.getMessage());
        }

        
        System.out.println("Valor final: " + contador);
    }
}
//podemos observar que los hilos van cada uno a su ritmo sin seguir un orden claro hasta alcanzar
//cada uno 1000 para obtener 5000 en total
