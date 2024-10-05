import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// Tarea 2 - Álvaro Fernández Amaro
public class Hilo extends Thread { //Aquí hacemos que la clase extienda (herede) la clase Thread para que funcione como hilo

    public Hilo() { //Constructor del hilo vacio en este caso

    }

    @Override
    public void run() { //Es necesario usar el metodo run

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //Declaro un objeto de tipo BufferedReader, que leerá la entrada del usuario, y además uso un InputStreamReader para convertir el flujo de bytes en un flujo de caracteres
        StringBuilder inputBuilder = new StringBuilder(); //El StringBuilder es una clase para realizar cadenas de texto más eficientes.
        String input = null; //Esto al ser null, lo que introduzca el usuario quedará almacenado en la variable input temporalmente.
        char terminationChar = '*'; //Declaro el caracter que hace que finalice la lectura de datos
        boolean terminated = false; //Declaro un bool para comprobar si ha terminado

        System.out.println("Introduce texto (finaliza con '*'):");//Pedimos al usuario que introduzca la cadena

        try { // Aquí usamos el manejo de errores diciendo que haga esto:

            while (!terminated) { // Inicio de bucle que se repetirá hasta que la variable terminated sea true.
                input = reader.readLine(); //Lee las lineas que se introducen
                for (int i = 0; i < input.length(); i++) {
                    if (input.charAt(i) == terminationChar) { //Si el caracter de terminación esta entre los caracteres escritos, finaliza el programa
                        terminated = true; // La variable terminated pasa a ser true
                        inputBuilder.append(input, 0, i); //Esto funciona de manera que si la última línea que se haya introducido, hay más texto antes del carácter de terminación, agregue todo hasta el '*'
                        break;
                    }
                }
                if (!terminated) {
                    inputBuilder.append(input).append("\n"); //Esto hace que mientras no haya acabado el bucle, o sea mientras que no encuentre el caracter de terminacion '*', que agregue todo al StringBuilder
                }
            }

            // Aquí simplemente muestro la información/texto que el usuario ha introducido.
            System.out.println("\nContenido ingresado por el usuario:");
            System.out.println("---------------------------------------\n");
            System.out.println(inputBuilder.toString());

        } catch (IOException e) { //En caso de que de error, lo muestre por pantalla:
            System.err.println("Error al leer la entrada: " + e.getMessage());
        }
    }
}
