public class Incrementar {

    private static int contador = 0;

    public static void main(String[] args) {
        Thread[] hilos = new Thread[5];


        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    contador++;
                }
            });
            hilos[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Valor final del contador: " + contador);
    }
}
