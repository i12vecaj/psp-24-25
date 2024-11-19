package UA2.tareas.dev_0.tarea_2.ua2tarea2fr3.src;
public class Main extends Thread {
    private static final CuentaCorriente cuenta = new CuentaCorriente();

    @Override
    public void run() {
        cuenta.anadirSaldo();
    }

    public static void main(String[] args) {
        int numHilos = 3;
        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Main();
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Operaciones finalizadas. Saldo final: " + cuenta.getSaldoCuenta());
    }
}
