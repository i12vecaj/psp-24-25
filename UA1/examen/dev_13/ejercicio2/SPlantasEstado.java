import java.util.Random;

public class SPlantasEstado implements Runnable {
    //Declaramos el numero de ciclos
    private static final int NUM_CYCLES = 10;
    private Random random = new Random();

    @Override
    public void run() {
        for (int i = 0; i < NUM_CYCLES; i++) {
            int value = random.nextInt(10 - 1 + 1) + 1;  // Estado de plantas entre 1 y 10
            long timestamp = System.currentTimeMillis();
            System.out.println("("+ timestamp +") Salud de las plantas (1-10): " + value);
            try {
                Thread.sleep(random.nextInt(3000 - 1000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("El estado de las plantas termino su lecturas.");
    }
}
