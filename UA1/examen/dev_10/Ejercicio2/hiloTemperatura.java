import java.util.Random;
public class hiloTemperatura implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int temperatura = random.nextInt(100);
            int tiempoLectura = random.nextInt(3) + 1;

            System.out.println("[" + System.currentTimeMillis() + "] Sensor de Temperatura - Lectura " + i + ": " + temperatura);

            try {
                Thread.sleep(tiempoLectura * 1000);
            } catch (InterruptedException e) {
                System.out.println("Sensor de Temperatura ha sido interrumpido.");
            }
        }
        System.out.println("Sensor de Temperatura ha completado sus 10 ciclos de lectura.");
    }
}