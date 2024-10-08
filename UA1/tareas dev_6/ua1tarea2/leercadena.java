import java.util.Scanner;

public class LeerCadena extends Thread {
    private String cadena = "";  // Almacenará la cadena leída

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Por favor, introduce una cadena de caracteres y termina con '*':");

            // FR1: Leer hasta encontrar el asterisco
            char caracter = scanner.next().charAt(0);  // Leer el primer carácter

            while (caracter != '*') {
                cadena += caracter;  // Añadir carácter a la cadena
                caracter = scanner.next().charAt(0);  // Leer el siguiente carácter
            }

            // FR2: Mostrar la cadena leída
            System.out.println("Cadena leída: " + cadena);
        }
    }

    public String getCadena() {
        return cadena;
    }
}


