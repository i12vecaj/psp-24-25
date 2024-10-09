import java.sql.SQLOutput;
import java.util.Scanner;

//Runnable sirve para poder crear un hilo.
public class Programa1 implements Runnable {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        //variable con StringBuilder sin valor.
        StringBuilder contenido = new StringBuilder();

        System.out.println("Escriba, cuando temine inserte: * \n");

        while (true){
            String texto = scanner.nextLine();

            //Terminara con *, se cierra el bucle.
            if (texto.endsWith("*")) { 
                contenido.append(texto.substring(0, texto.indexOf("*")));
                break;
            }else {
                //Añadir la linea a la cadena creada con StrignBuilder.
                contenido.append(texto).append(System.lineSeparator());
            }
        }
        System.out.println("El contenido es: \n" + contenido.toString() + "\n");
    }
}