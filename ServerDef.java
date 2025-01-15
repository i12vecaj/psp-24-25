import java.net.*;
import java.io.*;

public class ServerDef {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5678)) {
            System.out.println("Servidor esperando conexiones en el puerto 5678...");

            Socket cliente1 = serverSocket.accept();
            System.out.println("Cliente 1 conectado:");
            mostrarInformacionConexion(cliente1);

            DataInputStream entrada = new DataInputStream(cliente1.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente1.getOutputStream());

            salida.writeUTF("Hola Cliente 1");

            String mensajeCliente = entrada.readUTF();
            System.out.println("Mensaje del cliente: " + mensajeCliente);

            Socket cliente2 = serverSocket.accept();
            System.out.println("Cliente 2 conectado:");
            mostrarInformacionConexion(cliente2);

            entrada = new DataInputStream(cliente2.getInputStream());
            salida = new DataOutputStream(cliente2.getOutputStream());

            salida.writeUTF("Hola Cliente 2");

            mensajeCliente = entrada.readUTF();
            System.out.println("Mensaje del cliente: " + mensajeCliente);

            cliente1.close();
            cliente2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarInformacionConexion(Socket socket) {
        InetAddress remoteAddress = socket.getInetAddress();
        int remotePort = socket.getPort();
        int localPort = socket.getLocalPort();

        System.out.println("  Dirección remota: " + remoteAddress.getHostAddress());
        System.out.println("  Puerto remoto: " + remotePort);
        System.out.println("  Puerto local: " + localPort);
    }
}
