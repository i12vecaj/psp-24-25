import java.util.Random;
public class hiloEstadoPlantas implements Runnable {
    private int estadoPlantas;

    public hiloEstadoPlantas(int estadoInicial) {
        this.estadoPlantas = estadoInicial;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            estadoPlantas = random.nextInt(100);
            int tiempoLectura = random.nextInt(3) + 1;

            System.out.println("[" + System.currentTimeMillis() + "] Sensor de Estado de Plantas - Lectura " + i + ": " + estadoPlantas);

            try {
                Thread.sleep(tiempoLectura * 1000);
            } catch (InterruptedException e) {
                System.out.println("Sensor de Estado de Plantas ha sido interrumpido.");
            }
        }
        System.out.println("Sensor de Estado de Plantas ha hecho sus 10 ciclos de lectura.");
    }
}