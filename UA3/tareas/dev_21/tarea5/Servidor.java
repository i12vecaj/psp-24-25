import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado, esperando conexión...");

            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());

            Numeros numerosRecibidos = (Numeros) in.readObject();

            int numero = numerosRecibidos.getNumeroEntero();
            long cuadrado = (long) numero * numero;
            long cubo = (long) numero * numero * numero;

            numerosRecibidos.setCuadrado(cuadrado);
            numerosRecibidos.setCubo(cubo);

            out.writeObject(numerosRecibidos);
            out.flush();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
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
