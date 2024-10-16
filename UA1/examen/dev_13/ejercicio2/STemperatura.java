import java.util.Random;

public class STemperatura implements Runnable {
    //Declaramos el numero de ciclos
    private static final int NUM_CYCLES = 10;
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < NUM_CYCLES; i++) {
            int value = random.nextInt(50 - 15 + 1) + 15;  // Rango de temperatura entre 15 y 50
            long timestamp = System.currentTimeMillis();
            System.out.println("("+ timestamp +") Temperatura: " + value + "Cº");
            try {
                Thread.sleep(random.nextInt(3000 - 1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
        System.out.println("La temperatura termino su lecturas.");
    }
}
