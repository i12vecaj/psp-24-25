import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Programa_Alejandro_Sanchez_Quesada implements Runnable {

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder cadena; // Usado para almacenar la cadena leída
        char caracter; // Variable para almacenar cada carácter leído

        System.out.println("Introduce una frase o palabra y termina (*).Para poder salir escribe la palabra exists");

        try {
            // Es un bucle infinito asta que el usuario desee salir.
            while (true) {
                cadena = new StringBuilder(); // Se reinicia para introducir una nueva frase
                System.out.print("Cadena: "); // dice al usuario que introduzca una frase

                // Lee caracter por caracter asta ver (*)
                while ((caracter = (char) reader.read()) != '*') {
                    // Con exit sale del bucle
                    if (cadena.length() == 0 && caracter == 'e') {
                        // Volver a leer si el caracter es una 'e'
                        cadena.append(caracter);
                        if ((char) reader.read() == 'x' && (char) reader.read() == 'i' && (char) reader.read() == 't') {
                            System.out.println("Saliendo..."); // Mensaje de salida
                            return; // Terminar el hilo
                        }
                    } else {
                        cadena.append(caracter); // agrega el caracter a la cadena
                    }
                }

                // aqui muestra la frase pero sin el *
                System.out.println("Cadena leída: " + cadena.toString());

                reader.readLine(); // Esto evita problemas al leer la siguiente línea
            }
        } catch (IOException e) {
            // Control de errores 
            System.err.println("Error al leer la entrada: " + e.getMessage());
        }
    }
