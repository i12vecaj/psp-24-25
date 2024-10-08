import java.sql.SQLOutput;
import java.util.Scanner;
public class Hilo implements Runnable { //Implementamos Runnable para poder crear un hilo.
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder contenido = new StringBuilder();// Declaramos una variable con StringBuilder sin asignarle ningun valor ya que esta va a almacenar lo que introduzca el usuario.

        System.out.println("Introduzca el contenido que desea, cuando quiera teminar inserte: *\n");

        while (true){
            String texto = scanner.nextLine();

            if (texto.endsWith("*")) { //Aqui nos encargaremos de que si el contenido acaba con un *, se acabe el bucle.
                contenido.append(texto.substring(0, texto.indexOf("*")));
                break;
            }else {
                contenido.append(texto).append(System.lineSeparator());//Ahora añadiremos la linea a la cadena que hemos creado con StrignBuilder
            }
        }
        System.out.println("El contenido ha sido leido correctamente, se lo mostramos: \n" + contenido.toString() + "\n");
    }
}
