
import java.util.Scanner;
//importamos las libreiras que usaremos
public class Lector {
    public void entradaSalida() {
        Scanner entrada = new Scanner(System.in);// creamos un objeto de la clase Scanner para la entrada de datos
        StringBuilder cadenas = new StringBuilder();// creamos un objeto de la clase StringBuilder para el manejo de cadenas
        System.out.println("Ingresa los caracteres que quieras , pulsa * para terminar");
        while (true) {
            String cadena = entrada.nextLine();// entrada de datos
            for (char caracter : cadena.toCharArray()) {//recorrer la cadena
                if (caracter == '*') {// condicion para saber si se pulsa *
                    System.out.println(cadenas);
                    return;
                }
                cadenas.append(caracter);//ponemos las cadenas despues de que la condicion se cumpla
            }
        }
    }
}
