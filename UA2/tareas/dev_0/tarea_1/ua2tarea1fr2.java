public class Main {
    public static void main(String[] args) {
        Thread hilo = new Thread(new hiloPrincipal());
        hilo.start();
    }
}

class hiloPrincipal implements Runnable {
    int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo();
            hilo.start();
        }
    }

    class Hilo extends Thread {
        @Override
        public void run() {
            incrementar();
        }
    }

    public synchronized void incrementar() { // Utilizamos la función con la palabra clave synchronized para
        num += 1000;                         // permitiendo que mientras un hilo accede a una variable los otros esperen
        System.out.println(num);             // a que el otro termine de interactuar con la misma.
    }
}

/* Mientras que en la primera versión del código, aplica los cambios sobre las variables de forma desordenada
* en este medieante el uso del sync podemos sincronizar los procesos y hacer que se coordinen correctamente.*/

