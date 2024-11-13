public class ua2tarea1fr2 {
  //con la sincronizacion conseguimos un orden el cual sin el synchronized no teniamos 
    private static int contador = 0;
    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> incrementar("hilo1"));
        Thread hilo2 = new Thread(() -> incrementar("hilo2"));
        Thread hilo3 = new Thread(() -> incrementar("hilo3"));
        Thread hilo4 = new Thread(() -> incrementar("hilo4"));
        Thread hilo5 = new Thread(() -> incrementar("hilo5"));
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
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
        System.out.println("Valor final sincronizado: " + contador);
    }
    private static synchronized void incrementar(String nombreHilo) {
        for (int j = 0; j < 1000; j++) {
            contador++;
            System.out.println("soy" + nombreHilo + ", y he conseguido una puntuacion de: " + contador);
        }
    }
}
