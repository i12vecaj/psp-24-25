import java.util.Scanner;

public class Hilo implements Runnable {

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String cadena = "";  // Inicializamos una cadena vacia para ahora escribir sobre ella

        System.out.println("Introduce caracteres o palabras, usa '*' para salir:");

        while (true) {
            try {
                String input = scanner.nextLine();  // Lee la entrada completa del usuario

                // Si el input contiene '*', se acaba el bucle
                if (input.equals("*")) {
                    break;
                }

                // Añade el input a la cadena actual
                cadena += input;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Muestra la cadena leída
        System.out.println("Cadena leída: " + cadena);
    }
}
