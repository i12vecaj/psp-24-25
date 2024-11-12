public class IncrementoSincronizadoRunnable implements Runnable {
    private static int contador = 0;

    public static synchronized void incrementarContador() {
        contador++;
    }

    @Override
    public void run() {
        int incrementoLocal = 0; //variable para registrar el incremento por hilo
        for (int i = 0; i < 1000; i++) {
            incrementarContador();
            incrementoLocal++;
        }
        //imprimir el total incrementado por este hilo
        System.out.println(Thread.currentThread().getName() + " ha incrementado el contador en: " + incrementoLocal);
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread hilo = new Thread(new IncrementoSincronizadoRunnable(), "Hilo " + i); //nombro los hilos
            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error en la espera del hilo: " + e.getMessage());
            }
        }

        System.out.println("Resultado final con sincronización con Runnable: " + contador);
    }

    /*la diferencia de resultados no es visible, aunque se hace uso de runnable para que la clase actual
    pueda heredar de otras clases mientras se implementa runnable. se hace por flexibilidad en la herencia.
     */
}
