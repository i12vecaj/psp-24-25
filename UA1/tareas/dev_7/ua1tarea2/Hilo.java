import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Hilo extends Thread{
    public Hilo(){}
    List<String> Lineas = new ArrayList<String>(); // Declaramos el array donde guardaremos las líneas
    int numero = 0 ; //Este será el indice que le pongamos a nuestra líneas

    public void run() {
        Scanner scanner = new Scanner(System.in); //Declaramos e inicializamos nuestro scanner para leer las cadenas
        boolean salida = true; //Declramos e inicalizamos la salida del bucle
        String cadena; // Declaramos el String donde leeremos la cadena
        do {
            System.out.println("Introduce una cadena de caracteres");
            cadena= scanner.nextLine(); //Leemos la cadena
            salida = procesarEntrada(cadena); //LLamamos a una función que nos verificará si si sale o no del bucle

        }while (salida);
        String mensaje = "Información recogida por el sistema";
        System.out.println(mensaje);
        System.out.println("-".repeat(mensaje.length()));
        System.out.println(Lineas); //Mostramos las líneas por pantalla
    }

    public List<String> getLineas(){ //Este es un método que nos devuelve la líneas, lo utilizaremos para el testing
        return Lineas;
    }

    public boolean procesarEntrada(String cadena){ //Esta función que recibe un String nos devuele un boolean dependiendo de una condición
        if (cadena.equals("*")) { //Si la el parámetro que recibe es un * devuelve false
            return false;
        }
        else{ //Si la el parámetro que recibe no es * aumenta numero y añade lo que ha recibido a nuestra lista y devuelve true
            numero++;
            Lineas.add(numero + " - " + cadena) ;
            return true;
        }
    }

}
