package ua1tarea2;

import java.util.Scanner;

// Creamos una clase llamada HiloSimple que extiende de Thread para trabajar con hilos
public class ua1tarea2 extends Thread {

    @Override // Sobrescribimos el método 'run' para definir lo que hará nuestro hilo
    public void run() {
        // Creamos un objeto 'Scanner' para leer lo que escribe el usuario
        Scanner scanner = new Scanner(System.in);

        // Variable para guardar lo que el usuario escribe
        String cadena = "";
        // Variable para almacenar el resultado final
        String cadenaFinal = "";

        // Mientras el usuario no escriba "*", seguimos leyendo
        do {
            System.out.print("Escribe una cadena de caracteres (o * para terminar): ");
            cadena = scanner.nextLine(); // Leemos lo que el usuario escribe

            // Si lo que escribe no es "*", lo agregamos a cadenaFinal
            if (!cadena.equals("*")) {
                cadenaFinal += cadena + " "; // Concatenamos lo que escribe y añadimos un espacio
            }
        } while (!cadena.equals("*")); // El bucle se detiene cuando se escribe "*"

        // Cerramos el Scanner porque ya no necesitamos leer más
        scanner.close();

        // Mostramos en la pantalla la cadena final que se ha construido
        System.out.println("Texto completo: " + cadenaFinal);
    }
}
