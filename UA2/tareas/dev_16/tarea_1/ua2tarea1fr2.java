public class ua2tarea1fr2 extends Thread {
    //Vuelvo a compartir la misma variable para todos los hilos.
    static int contador = 0;

    //Creo un método sincronizado para que incremnete el contador de forma segura.
    public static synchronized void incrementar() {
        for (int i = 0; i < 1000; i++) {
            contador++;
        }
    }

    @Override
    public void run() {
        incrementar(); //Cada hilo llama al método.
    }

    public static void main(String[] args) {
        //Otro array para almacenar los hilos.
        ua2tarea1fr2[] hilos = new ua2tarea1fr2[5];

        //Por segunda vez creo y lanzo los hilos.
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new ua2tarea1fr2();
            hilos[i].start();
        }

        //Y esperamos otra vez a que cada hilo termine.
        for (ua2tarea1fr2 hilo : hilos) {
            try {
                hilo.join(); //"Join" asegura de que el hilo padre espere los hilos hijos.
            } catch (InterruptedException e) {
                System.err.println("Error al unir el hilo: " + e.getMessage());
            }
        }

        //Y vuelvo a mostrar el valor final del contador con la sincronización ya hecha.
        System.out.println("Valor final del contador (con sincronización en Thread): " + contador);
    }
}