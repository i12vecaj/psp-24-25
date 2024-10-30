import java.util.Random;
public class hiloHumedad implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int humedad = random.nextInt(100);
            int tiempoLectura = random.nextInt(3) + 1;

            System.out.println("[" + System.currentTimeMillis() + "] Sensor de Humedad - Lectura " + i + ": " + humedad);

            try {
                Thread.sleep(tiempoLectura * 1000);
            } catch (InterruptedException e) {
                System.out.println("Sensor de Humedad ha sido interrumpido.");
            }
        }
        System.out.println("Sensor de Humedad ha completado sus 10 ciclos de lectura.");
    }
}