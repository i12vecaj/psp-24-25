import java.util.Scanner;

public class Ejecutar implements Runnable {  // Implementar Runnable para crear un hilo
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        //creamos una variable cadena con stringBuilder que empieza con una cadena vacia y se va rellenado
        //a medida que se le añadan caracteres.
        StringBuilder cadena = new StringBuilder();
        char caracter;

        System.out.println("Introduce caracteres (termina con '*'):");

        // Bucle hasta que encuentre '*'
        while (true) {
            String texto = scanner.nextLine();  // Leer toda la línea de entrada
            for (int i = 0; i < texto.length(); i++) {
                // Obtener cada carácter con charAt(i) empieza en la posición 0 y recorre hasta el *
                caracter = texto.charAt(i);
                if (caracter == '*') {
                    System.out.println("Cadena leída: " + cadena.toString());  // Mostrar la cadena leída
                    scanner.close();  // Cerrar el escáner
                    return;  // Terminar el hilo al encontrar el asterisco
                }
                cadena.append(caracter);  // Añadir carácter a la cadena
            }
            cadena.append('\n');  // Añadir salto de línea después de cada línea
        }
    }
}
