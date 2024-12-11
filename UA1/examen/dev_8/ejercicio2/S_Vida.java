import java.util.Random;

public class S_Vida implements Runnable {
    private Random porcentaje = new Random();
    private static final int MAX_ITERATIONS = 10; // ciclos de uso = 10

    @Override
    public void run() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            int vida = porcentaje.nextInt(101); // genera porcentaje random
            System.out.println("Vida actual: " + vida + "%");

            try {
                Thread.sleep(2000); // espera 2 segundos antes de  comprobar la temperatura
            } catch (InterruptedException e) {
                System.out.println("ERROR Hilo.");
                break; // salir del bucle
            }
        }
        System.out.println("Hilo de vida finalizado.");
    }
}