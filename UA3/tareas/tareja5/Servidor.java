import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            // Crear el servidor en el puerto 5000
            serverSocket = new ServerSocket(5000);
            System.out.println("Esperando conexión del cliente...");

            // Aceptar la conexión
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            // Crear los streams para recibir y enviar objetos
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());

            // Leer el objeto Numeros enviado por el cliente
            Numeros numeros = (Numeros) in.readObject();

            // Realizar los cálculos (ya se calculó el cuadrado y cubo en el constructor)
            // En este caso, solo reenviamos el objeto con los resultados
            out.writeObject(numeros);
            out.flush();

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al recibir el objeto: " + e.getMessage());
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
