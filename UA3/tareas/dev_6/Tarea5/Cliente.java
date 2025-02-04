import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce un número entero: ");
            int numero = Integer.parseInt(reader.readLine());

            if (numero < 0) {
                System.out.println("El número debe ser mayor o igual a 0.");
                return;
            }

            // Enviar objeto al servidor
            Numeros numeros = new Numeros(numero);
            out.writeObject(numeros);

            // Recibir objeto modificado del servidor
            Numeros respuesta = (Numeros) in.readObject();
            System.out.println("Cuadrado: " + respuesta.getCuadrado());
            System.out.println("Cubo: " + respuesta.getCubo());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        }
    }
}

