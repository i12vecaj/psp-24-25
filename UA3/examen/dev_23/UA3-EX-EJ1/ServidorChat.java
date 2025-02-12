import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    private static Set<ManejadorCliente> manejadoresClientes = new HashSet<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket servidorSocket = new ServerSocket(12345)) {
            System.out.println("Servidor activo en el puerto 12345...");

            while (true) {
                Socket socket = servidorSocket.accept();
                ManejadorCliente manejadorCliente = new ManejadorCliente(socket);
                manejadoresClientes.add(manejadorCliente);
                new Thread(manejadorCliente).start();
            }
        }
    }

    static void transmitirMensaje(String mensaje, ManejadorCliente remitente) {
        for (ManejadorCliente manejadorCliente : manejadoresClientes) {
            if (manejadorCliente != remitente) {
                manejadorCliente.enviarMensaje(mensaje);
            }
        }
        System.out.println(mensaje);
    }

    static void eliminarCliente(ManejadorCliente manejadorCliente) {
        manejadoresClientes.remove(manejadorCliente);
    }
}

//Hilo para manejar los clientes que se conectan al servidor
class ManejadorCliente implements Runnable {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public ManejadorCliente(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
    }

    public void run() {
        try {
            String mensaje;
            while ((mensaje = in.readUTF()) != null) {
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    break;
                }
                ServidorChat.transmitirMensaje(mensaje, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServidorChat.eliminarCliente(this);
        }
    }

    void enviarMensaje(String mensaje) {
        try {
            out.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
