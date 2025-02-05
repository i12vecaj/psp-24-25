import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            System.out.print("Introduce un número entero: ");
            int numero = scanner.nextInt();

            if (numero < 0) {
                System.out.println("El número debe ser mayor o igual a 0.");
                return;
            }

            Numeros numeros = new Numeros(numero);

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(numeros);
            out.flush();

            Numeros resultado = (Numeros) in.readObject();

            System.out.println("Número introducido: " + resultado.getNumeroEntero());
            System.out.println("Cuadrado: " + resultado.getCuadrado());
            System.out.println("Cubo: " + resultado.getCubo());

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al comunicar con el servidor: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
