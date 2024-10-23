import java.util.Random;

public class S_Temperatura implements Runnable {
    private Random random = new Random();
    private static final int MAX_ITERATIONS = 10; // ciclos de uso = 10

    @Override
    public void run() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            int temperatura = random.nextInt(46); // temperatura entre 0 y 44
            System.out.println("Temperatura actual: " + temperatura + "°C");

            try {
                Thread.sleep(2000); // espera 2 segundos antes de  comprobar la temperatura
            } catch (InterruptedException e) {
                System.out.println("ERROR Hilo.");
                break; // salir del bucle
            }
        }
        System.out.println("Hilo de temperatura finalizado.");
    }
}
