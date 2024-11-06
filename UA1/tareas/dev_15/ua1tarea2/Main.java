import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Utilizamos Scanner para leer lo que el usuario escriba
        Scanner scanner = new Scanner(System.in);

        // Usamos StringBuilder para guardar la cadena que se va ingresando
        StringBuilder input = new StringBuilder();

        System.out.println("Introduce una cadena de texto (termina con *):");

        // Bucle que almacena el texto hasta encontrar un *
        while (true) {
            // Leemos la línea que se acaba de escribir
            String line = scanner.nextLine();

            // Recorremos todos los carácteres de esa línea
            for (char ch : line.toCharArray()) {
                // Si encontramos un * mostramos todo
                if (ch == '*') {
                    System.out.println("Cadena completa: " + input.toString());
                    return;
                }
                // Si hay * añadimos a  cadena
                input.append(ch);
            }
        }
    }
}