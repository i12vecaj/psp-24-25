import java.util.Scanner;

public class javas1 extends Thread{
    public static void main(String[] args) {
        leerCadena();
    }
    //esta funcion nos va a permotir leer la cadena de caracteres
    public static void leerCadena() {
        Scanner escaner = new Scanner(System.in);
        StringBuilder cadena = new StringBuilder(); // Utilizamos StringBuilder para eficiencia
        System.out.println("en este programa puedes introducir texto , pero este programa acaba cuando pones * :");

        try {
            while (true) {
                String input = escaner.nextLine(); // Leer línea de entrada
                if (input.contains("*")) { // Verificar si la línea contiene un asterisco
                    int index = input.indexOf('*');
                    cadena.append(input, 0, index);
                    break; // Salir del bucle si encontramos el asterisco
                }
                cadena.append(input).append("\n"); // Añadir la línea a la cadena
            }

            System.out.println("\nCadena completa leída:");
            System.out.println(cadena.toString()); // mostramos la cadena

        } catch (Exception error) {
            System.out.println("Se ha producido un error: " + error.getMessage());
        } finally {
            escaner.close(); // Cerrar el scanner para liberar recursos
        }
    }
}
