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
            // Pedir al usuario el número
            System.out.print("Introduce un número: ");
            int numero = scanner.nextInt();

            // Validar si el número es mayor o igual a 0
            if (numero < 0) {
                System.out.println("El número debe ser mayor o igual a 0.");
                return;
            }

            // Crear el objeto Numeros con el número ingresado
            Numeros numeros = new Numeros(numero);

            // Conectar al servidor
            socket = new Socket("localhost", 5000); // Asegúrate de que el servidor esté en el puerto 5000
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            // Enviar el objeto Numeros al servidor
            out.writeObject(numeros);
            out.flush();

            // Recibir el objeto Numeros de vuelta del servidor
            Numeros resultado = (Numeros) in.readObject();

            // Mostrar los resultados
            System.out.println("Resultados del servidor: " + resultado);

        } catch (IOException e) {
            System.out.println("Error de conexión con el servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al recibir el objeto: " + e.getMessage());
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
