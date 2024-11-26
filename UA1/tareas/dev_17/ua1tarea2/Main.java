import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
    Thread hilo = new Thread(new Hilo()); //Creamos la instancia del hilo de nuestra clase.

    hilo.start();//Iniciamos el hilo.

    try {
        hilo.join(); //Esperamos a que termine el hilo para continuar con el resto del programa.

    } catch (InterruptedException e) {//Usaremos catch por si surge algun error indicarlo al usuario.
        System.out.println("Parece ser que el hilo se ha interrumpido.");

    }
        System.out.println("Gracias por usar nuestro programa, un saludo.");
    }
}
