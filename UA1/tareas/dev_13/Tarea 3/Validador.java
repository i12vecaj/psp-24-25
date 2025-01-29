import java.util.Scanner;

public class Validador {
    public static void main(String[] args) {
        // Verificar el número de argumentos
        if (args.length < 1) {
            System.exit(1);
        }

        // Validar el primer argumento
        String argumento = args[0];

        // Verificar si es un número entero
        try {
            int numero = Integer.parseInt(argumento);
            if (numero < 0) {
                System.exit(3);
            }
        } catch (NumberFormatException e) {
            // Si no se puede convertir a número, es una cadena
            System.exit(2);
        }

        // Si no se cumplen ninguna de las condiciones anteriores
        System.exit(0);
    }
}
