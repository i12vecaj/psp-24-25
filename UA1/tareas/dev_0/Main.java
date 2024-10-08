import java.util.Scanner;

public class Main {

    // definicion del hilo para leer caracteres de entrada
    static class LectorDeCaracteres implements Runnable {
        private StringBuilder cadenaLeida;

        public LectorDeCaracteres() {
            this.cadenaLeida = new StringBuilder();
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            char caracter;

            System.out.println("Introduce caracteres. Para finalizar, introduce un asterisco (*):");

            //leer cadena hasta *
            while (true) {
                try {
                    caracter = scanner.next().charAt(0);
                    if (caracter == '*') {
                        break;
                    }

                    //añadir caracter a cadena
                    cadenaLeida.append(caracter);

                    //catch para error

                } catch (Exception e) {
                    System.err.println("Error al leer el caracter: " + e.getMessage());
                    break;
                }
            }

            // imprimir cadena
            System.out.println("Cadena leida: " + cadenaLeida.toString());

            scanner.close();
        }
    }

    public static void main(String[] args) {
        // hilo que ejecuta lector
        Thread hiloLector = new Thread(new LectorDeCaracteres());

        // arrancar hilo
        hiloLector.start();
        try {
            hiloLector.join();
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }

        System.out.println("La lectura ha finalizado.");
    }
}
