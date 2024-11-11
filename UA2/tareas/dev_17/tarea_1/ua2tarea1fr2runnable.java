public class ua2tarea1fr2runnable {
    public static void main(String[] args) {

        Contador contador = new Contador();

        Thread hilo1 = new Thread (new HiloIncrementador(contador, 1000));
        Thread hilo2 = new Thread (new HiloIncrementador(contador, 1000));
        Thread hilo3 = new Thread (new HiloIncrementador(contador, 1000));
        Thread hilo4 = new Thread (new HiloIncrementador(contador, 1000));
        Thread hilo5 = new Thread (new HiloIncrementador(contador, 1000));

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
            e.printStackTrace();
        }
    }

    // Hacemos que HiloIncrementador sea una clase estática interna
    public static class HiloIncrementador implements Runnable {
        private Contador contador;
        private int valorAIncrementar;

        public HiloIncrementador(Contador contador, int valorAIncrementar) {
            this.contador = contador;
            this.valorAIncrementar = valorAIncrementar;
        }

        @Override
        public void run() {
            contador.incrementarValor(valorAIncrementar);
        }
    }

    // Hacemos que Contador sea una clase estática interna
    public static class Contador {
        private int valor_contador;

        public Contador() {
            this.valor_contador = 0;
        }

        public synchronized void incrementarValor(int valorAIncrementar) {
            System.out.println("El valor actual del contador es: " + valor_contador);
            valor_contador += valorAIncrementar;

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}