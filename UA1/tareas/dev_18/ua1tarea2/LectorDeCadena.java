import java.util.Scanner;
public class LectorDeCadena extends Thread {

    public LectorDeCadena () {
        
    }

    
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            String cadenaLeida = ""; // Aquí guardamos los caracteres
            char caracter; // Variable para guardar el carácter
    
            System.out.println("Introduce caracteres (termina con *):");
    
            //Leer caracteres hasta encontrar(*)
            while (true) {
                caracter = scanner.next().charAt(0);
                
                // Si el carácter es un asterisco, salir del bucle
                if (caracter == '*') {
                    break;
                }
                
                // Añadir el carácter a la cadena
                cadenaLeida += caracter;
            }
    
            // Mostrar la cadena cuando el asterisco
            System.out.println("Cadena leída: " + cadenaLeida);
            
        
            scanner.close();
        }


}
