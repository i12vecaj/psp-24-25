public class ua2tarea1fr2runnable implements Runnable {
    //La misma varable de los dos códigos a anteriores.
    static int contador = 0;


    //El mismo método sincornizado para incrementar el contador.
    public static synchronized void incrementar() {
        for (int i = 0; i < 1000; i++) {
            contador++;
        }
    }

    @Override
    public void run() {
        incrementar(); //Cada hilo ejecuta el método sincronizado.
    }

    public static void main(String[] args) {
        //El array de hilos.
        Thread[] hilos = new Thread[5];

        //Vuelvo a crear y lanzar los hilos usando Runnable.
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new ua2tarea1fr2runnable());
            hilos[i].start();
        }

        //De nuevo esperamos a que todos los hilos terminen.
        for (Thread hilo : hilos) {
            try {
                hilo.join(); //"Join" para que el hilo papá espere al hijo hijo.
            } catch (InterruptedException e) {
                System.err.println("Error al unir el hilo: " + e.getMessage());
            }
        }

        //Y pro último, mostramos el valor del contador final.
        System.out.println("Valor final del contador (con sincronizacion en Runnable): " + contador);
    }
}