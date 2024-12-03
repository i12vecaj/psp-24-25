public class ua2tarea1fr2 {

    private static int contador = 0;    // variable que se comparte entre todos los hilos

    public static void main(String[] args) {
        // Crear un aray de hilos
        Thread[] hilos = new Thread[5];


        for (int i = 0; i < 5; i++) { // se inicia el hilo y se lanza
            hilos[i] = new Thread(new Incrementador());
            hilos[i].start(); // inici cada hilo
        }


        for (Thread hilo : hilos) {
            try {
                hilo.join(); // Espera a que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace(); // se imprime el error en caso de interrupcion
            }
        }


        System.out.println("Valor final del contador: " + contador); // se imprime el valor
    }


    static class Incrementador extends Thread { // clase Incrementador que extiende Thread
        public void run() {

            for (int i = 0; i < 1000; i++) { // incrementar el contador 1000 veces
                incrementarContador();
            }
        }


        private synchronized void incrementarContador() { // metodo sincronisado para incrementar el contador
            contador++;
        }
    }
}
