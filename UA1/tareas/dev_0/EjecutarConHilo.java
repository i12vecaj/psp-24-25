//ejecutor del codigo main

public class EjecutarConHilo {

    //clase que ejecuta el hilo
    static class EjecutorPrograma implements Runnable {

        @Override
        public void run() {
            // llamada al metodo main
            Main.main(null);
        }
    }

    public static void main(String[] args) {
        //crear hilo para leer
        Thread hiloEjecutor = new Thread(new EjecutorPrograma());

        // arrancar el hilo
        hiloEjecutor.start();

        // control de errores
        try {
            hiloEjecutor.join();
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        System.out.println("El programa ha finalizado.");
    }
}
