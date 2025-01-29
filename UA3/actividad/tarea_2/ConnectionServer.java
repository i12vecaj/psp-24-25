import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionServer {
    public static void main(String[] args) throws IOException {
        int portNumber = 4000;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Esperando conexiones de clientes...");

        for (int clientCount = 1; clientCount <= 2; clientCount++) {
            Socket connectedClient = serverSocket.accept();
            System.out.println("******************************");
            System.out.println("Cliente " + clientCount + " conectado.");

            DataInputStream input = new DataInputStream(connectedClient.getInputStream());
            DataOutputStream output = new DataOutputStream(connectedClient.getOutputStream());

            System.out.println("Dirección remota: " + connectedClient.getInetAddress().getHostAddress() + ":" + connectedClient.getPort());
            System.out.println("Dirección local: " + connectedClient.getLocalAddress().getHostAddress() + ":" + connectedClient.getLocalPort());

            String clientMessage = input.readUTF();
            output.writeUTF("Saludos desde el servidor a este cliente");

            System.out.println("Mensaje recibido del CLIENTE " + clientCount + ": \n\t" + clientMessage);
            System.out.println("******************************");

            input.close();
            output.close();
            connectedClient.close();
        }

        serverSocket.close();
    }
}
