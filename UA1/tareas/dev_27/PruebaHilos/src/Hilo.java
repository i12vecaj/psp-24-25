public class Hilo implements Runnable {

    @Override
    public void run() {
        int numero = 0;  // Inicializamos una cadena vacia

        while (true) {
            try {
                numero++;
                System.out.println(numero);
                if (numero==30) {
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
