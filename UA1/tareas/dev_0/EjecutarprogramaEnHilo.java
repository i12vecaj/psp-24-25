public class EjecutarProgramaEnHilo implements Runnable {

    @Override
    public void run() {

        LecturaCadena.main(null);
    }

    public static void main(String[] args) {

        Thread hiloEjecucion = new Thread(new EjecutarProgramaEnHilo());
        hiloEjecucion.start();

        try {
            hiloEjecucion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
