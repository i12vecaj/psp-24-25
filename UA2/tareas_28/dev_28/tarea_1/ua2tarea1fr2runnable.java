public class ua2tarea1fr2runnable {

    private static int contador = 0; // variable que se comparte entre todos los hilos

    public static void main(String[] args) {
        // Crear un array de hilos
        Thread[] hilos = new Thread[5];
        Incrementador incrementador = new Incrementador();


        for (int i = 0; i < 5; i++) { // se inicia el hilo y se lanza
            hilos[i] = new Thread(incrementador);
            hilos[i].start(); // iniciar cada hilo
        }


        for (Thread hilo : hilos) { // Espera a que todos los hilos termine
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace(); // se imprime el error en caso de interrupcion
            }
        }


        System.out.println("Valor final del contador: " + contador); // se imprime el valor
    }


    static class Incrementador implements Runnable { //clase incrementador que implementa runable
        public void run() {

            for (int i = 0; i < 1000; i++) {  // incrementar el contador 1000 veces
                incrementarContador();
            }
        }


        private synchronized void incrementarContador() {  // Método sincronizado para incrementar el contador
            contador++;
        }
    }
}
