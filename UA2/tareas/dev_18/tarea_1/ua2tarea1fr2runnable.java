public class ua2tarea1fr2runnable {
  //con la sincronizacion conseguimos un orden el cual sin el synchronized no teniamos 
  //en lo unico que se diferencia este codigo del otro simplemente es en que este es el runnable
    private static int contador = 0;
    public static void main(String[] args) {
        Runnable tarea = new Incrementador();
        Thread hilo1 = new Thread(tarea, "hilo1");
        Thread hilo2 = new Thread(tarea, "hilo2");
        Thread hilo3 = new Thread(tarea, "hilo3");
        Thread hilo4 = new Thread(tarea, "hilo4");
        Thread hilo5 = new Thread(tarea, "hilo5");
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
        private synchronized void incrementar(String nombreHilo) {
            for (int j = 0; j < 1000; j++) {
                contador++;
                System.out.println("soy" + nombreHilo + ", y he conseguido una puntuacion de: " + contador);
            }   
  static class Incrementador implements Runnable {
        @Override
        public void run() {
            incrementar(Thread.currentThread().getName());//utilizamos incrementar para que se vaya como si misma se llama imcrementando la puntuacion y el hilo ya que va de descendente a ascendente
        }
        }
    }
}
