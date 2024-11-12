public class IncrementoSincronizadoThread extends Thread {
    private static int contador = 0; //variable compartida

    // metodo sincronizado para incrementar el contador
    public static synchronized void incrementarContador() {
        contador++;
    }

    @Override
    public void run() {
        int incrementoLocal = 0; //variable local para contar los incrementos de este hilo
        for (int i = 0; i < 1000; i++) {
            incrementarContador();
            incrementoLocal++;
        }
        //imprimir el total incrementado por este hilo al finalizar
        System.out.println(Thread.currentThread().getName() + " incrementó el contador en: " + incrementoLocal);
    }

    public static void main(String[] args) {
        //creamos y lanzamos 5 hilos
        IncrementoSincronizadoThread hilo1 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo2 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo3 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo4 = new IncrementoSincronizadoThread();
        IncrementoSincronizadoThread hilo5 = new IncrementoSincronizadoThread();

        //nombramos los hilos para identificarlos en la salida
        hilo1.setName("Hilo 1");
        hilo2.setName("Hilo 2");
        hilo3.setName("Hilo 3");
        hilo4.setName("Hilo 4");
        hilo5.setName("Hilo 5");

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

        //resultado final
        System.out.println("Resultado final con sincronización con thread: " + contador);
        
        /*en esta ocasión, después de ejecutar el programa unas 10 veces, los hilos incrementan en 1000
        hasta llegar al resultado esperado, que es 5000.
         */
    }
}
