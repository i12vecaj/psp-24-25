public class ua2tarea1fr1 {
    static class Incrementar implements Runnable {
        private int resultado;
        private String nombreHilo;

        public Incrementar(String nombre, int valorInicial) {
            this.nombreHilo = nombre;
            this.resultado = valorInicial;
        }

        @Override
        public void run() {
            System.out.println("El valor es " + resultado + " en " + nombreHilo);
        }
    }

    public static void main(String[] args) {
        Thread[] hilo = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int valorInicial = (i + 1) * 1000;
            hilo[i] = new Thread(new Incrementar("Hilo-" + (i + 1), valorInicial));
            hilo[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                hilo[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
