import java.util.Random;

public class SHumedad implements Runnable {
    //Declaramos el numero de ciclos
    private static final int NUM_CYCLES = 10;
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < NUM_CYCLES; i++) {
            int value = random.nextInt(90 - 15 + 1) + 5;  // Rango de humedad entre 15 y 90
            long timestamp = System.currentTimeMillis();
            System.out.println("("+ timestamp +") Humedad: " + value + "%");
            try {
                Thread.sleep(random.nextInt(3000 - 1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("La humedad termino su lecturas.");
    }
}