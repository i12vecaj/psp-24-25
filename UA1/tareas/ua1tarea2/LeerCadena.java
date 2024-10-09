import java.util.Scanner;

/**
 * lea una cadena de caracteres desde la entrada estándar hasta recibir un carácter de terminación,
 * en concreto, un asterisco "*"
 */
public class LeerCadena {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();

        System.out.println("Escribre el texto y para Finalizar introduce * y luego Intro):");

        // Leer la entrada en una sola línea
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Si se introduce un asterisco, finalizar la lectura
            if (line.contains("*")) {

                // Almacenar solo la parte de la línea antes del asterisco
                input.append(line.substring(0, line.indexOf("*"))).append("\n");
                break;
            }

            // Almacenar la línea leída
            input.append(line).append("\n");
        }

        // Mostrar la información leída
        System.out.println("Información introducida:");
        System.out.println(input.toString().trim()); // Eliminar el último salto de línea

        // Cerrar el scanner
        scanner.close();
    }
}