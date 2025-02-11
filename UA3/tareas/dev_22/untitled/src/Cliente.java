import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int numero1 = scanner.nextInt();

        if (numero1 < 0) {
            System.err.println("El numero debe ser mayor o igual a 0.");
            return;
        }

        Numeros numeros = new Numeros();
        numeros.setNumero1(numero1);

        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream objetoenviado = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream objetorecivido = new ObjectInputStream(socket.getInputStream())) {

            objetoenviado.writeObject(numeros);
            Numeros resultado = (Numeros) objetorecivido.readObject();

            System.out.println("El cuadrado es: " + resultado.getNumero2());
            System.out.println("El cubo es: " + resultado.getNumero3());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error con el servidor: " + e.getMessage());
        }
    }
}