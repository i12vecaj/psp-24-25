public class ua2tarea1fr1{
    
    private static int contador = 0;

    public static void main(String[] args) {
      //creacion de cada hilo y los puntos que se le asigna
        Thread hilo1 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("Hilo1[Mis puntos son: " + contador);
            }    
        });
        hilo1.start();
        Thread hilo2 = new Thread(() -> {
             for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("Hilo2[Mis puntos son: " + contador);
            }
        });
        hilo2.start();
        Thread hilo3 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("Hilo3[Mis puntos son:" + contador);
            }
        });
        hilo3.start();
        Thread hilo4 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("Hilo4[Mis puntos son: " + contador);
            }    
        });
        hilo4.start();
        Thread hilo5 = new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                contador++;
                 System.out.println("Hilo5[Mis puntos son: " + contador);
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
            System.out.println("Ha habido un error en este hilo: " + e.getMessage());
        } 
     System.out.println("Valor final: " + contador);
    }
}
