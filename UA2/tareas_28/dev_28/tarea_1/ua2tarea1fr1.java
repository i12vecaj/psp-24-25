public class ua2tarea1fr1 {

    private static int contador = 0; // esto es una variable la se comparte entre todos los hilos

    public static void main(String[] args) {

        Thread[] hilos = new Thread[5]; // creo el array

        // Inicializar y lanzar los hilos
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new Incrementador());
            hilos[i].start(); // inicio cada hilo
        }


        for (Thread hilo : hilos) {
            try { // hay esperar que termine todos los hilos
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace(); // aqui ponemos un error en el caso de se interrumpa
            }
        }


        System.out.println("Valor final del contador: " + contador);
    }


    static class Incrementador implements Runnable { // esta es la clase del incrementador que tiene runnable
        public void run() {

            for (int i = 0; i < 1000; i++) { // se incrementa 1000 veces
                contador++;
            }
        }
    }
}

