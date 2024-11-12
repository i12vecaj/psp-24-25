public class IncrementoSincronizadoThread extends Thread {
    private static int contador = 0; // Variable compartida

    //metodo sincronizado para incrementar el contador
    public static synchronized void incrementarContador() {
        contador++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementarContador();
        }
    }

    public static void main(String[] args) {
        //creamos y lanzamos 5 hilos individualmente
        IncrementoSincronizadoThread hilo1 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo2 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo3 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo4 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo5 = new IncrementoSincronizadoThread();

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
            System.out.println("Error en la espera de los hilos: " + e.getMessage());
        }

        System.out.println("Resultado final con sincronización usando Thread: " + contador);
    }
}
