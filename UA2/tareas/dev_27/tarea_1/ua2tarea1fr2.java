public class ua2tarea1fr2 {
    static class Incrementar implements Runnable {
        private int resultado;
        private String nombreHilo;

        public Incrementar(String nombre, int valorInicial) {
            this.nombreHilo = nombre;
            this.resultado = valorInicial;
        }
        public synchronized void incrementarValor(int incremento) {
            resultado += incremento;
            System.out.println("El valor incrementado es " + resultado + " en " + nombreHilo);
        }

        @Override
        public void run() {
            incrementarValor(10);
        }
    }

    public static void main(String[] args) {
        System.out.println("Ejecución usando Thread:");
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
        System.out.println("\nEjecución usando Runnable:");
        Thread[] hilosRunnable = new Thread[5];
        for (int i = 0; i < 5; i++) {
            int valorInicial = (i + 1) * 1000;
            Runnable tarea = new Incrementar("Hilo-Runnable-" + (i + 1), valorInicial);
            hilosRunnable[i] = new Thread(tarea);
            hilosRunnable[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                hilosRunnable[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}