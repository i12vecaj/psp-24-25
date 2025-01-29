import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        final int PORT = 5000;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor TCP esperando conexiones en el puerto " + PORT);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

                    System.out.println("Cliente conectado desde " + socket.getInetAddress());

                    Numeros numeros = (Numeros) in.readObject();
                    if (numeros.getNumero() < 0) {
                        System.out.println("Número negativo recibido, cerrando conexión.");
                        out.writeObject(null);
                        continue;
                    }

                    int num = numeros.getNumero();
                    numeros.setCuadrado((long) num * num);
                    numeros.setCubo((long) num * num * num);

                    System.out.println("Procesado: " + numeros);

                    out.writeObject(numeros);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error iniciando el servidor: " + e.getMessage());
        }
    }
}