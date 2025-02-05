import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor: esperando conexiones...");

                try (Socket clientSocket = serverSocket.accept();
                     ObjectInputStream objetorecivido = new ObjectInputStream(clientSocket.getInputStream());
                     ObjectOutputStream objetoenviado = new ObjectOutputStream(clientSocket.getOutputStream())) {

                    Numeros numeros = (Numeros) objetorecivido.readObject();
                    numeros.setNumero2((long) Math.pow(numeros.getNumero1(), 2));
                    numeros.setNumero3((long) Math.pow(numeros.getNumero1(), 3));

                    objetoenviado.writeObject(numeros);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error en la comunicacion: " + e.getMessage());
                }

        } catch (IOException e) {
            System.err.println("Error del servidor: " + e.getMessage());
        }
    }
}