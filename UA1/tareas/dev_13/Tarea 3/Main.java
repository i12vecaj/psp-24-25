import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Asegurarse de que se pase al menos un argumento al programa Validador
        if (args.length < 1) {
            System.out.println("Por favor, proporciona un argumento para validar.");
            return;
        }

        // Construir el comando para ejecutar el programa Validador
        String command = "java Validador " + args[0];

        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();

            // Mostrar el resultado según el código de salida
            switch (exitCode) {
                case 1:
                    System.out.println("Error: El número de argumentos es menor que 1.");
                    break;
                case 2:
                    System.out.println("Error: El argumento es una cadena.");
                    break;
                case 3:
                    System.out.println("Error: El argumento es un número entero menor que 0.");
                    break;
                case 0:
                    System.out.println("El argumento es válido.");
                    break;
                default:
                    System.out.println("Código de salida desconocido: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al ejecutar el programa Validador: " + e.getMessage());
        }
    }
}
