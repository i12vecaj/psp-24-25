import java.net.*;
import java.io.*;

public class ClientDef {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5678)) {
            System.out.println("Conexión establecida con el servidor.");
            mostrarInformacionConexion(socket);

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            String mensajeServidor = entrada.readUTF();
            System.out.println("Mensaje del servidor: " + mensajeServidor);

            salida.writeUTF("¡Hola Servidor!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mostrarInformacionConexion(Socket socket) {
        InetAddress remoteAddress = socket.getInetAddress();
        int remotePort = socket.getPort();
        int localPort = socket.getLocalPort();

        System.out.println("Conexión establecida:");
        System.out.println("  Dirección IP remota: " + remoteAddress.getHostAddress());
        System.out.println("  Puerto remoto: " + remotePort);
        System.out.println("  Puerto local: " + localPort);
    }
}
