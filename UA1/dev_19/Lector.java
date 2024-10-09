import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//importamos las libreiras que usaremos
public class Lector {
    public void entradaSalida() {
        List<String> lista = new ArrayList<>(); // creamos una lista para introducir los strings
        Scanner scanner = new Scanner(System.in);// creamos un scanner para introducir el texto
        System.out.println("Por favor introduzca el texto que quiera, cuando quiera finalizar introduzca un * ");
        while (scanner.hasNext()) { // creamos un bucle  donde indicamos la entrada de datos se repetira hasta que se introduzca un *
            String next = scanner.next();
            if (next.equals("*")) {
                break;
            }
            lista.add(next);
        }
        System.out.println("El texto introducido es: " + lista.toString());//mostramos por pantallas
    }
}
