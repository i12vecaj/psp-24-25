import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {
    private static final int PUERTO = 10000;
    private static ArrayList<Socket> clientes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Servidor a la escucha en el puerto " + PUERTO + "...");

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                clientes.add(cliente);
                manejarCliente(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Funcion que maneja los clientes, controla la salida del bucle
    private static void manejarCliente(Socket cliente) {
        try {
            InputStream entrada = cliente.getInputStream();
            OutputStream salida = cliente.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead = entrada.read(buffer);
            String mensaje = new String(buffer, 0, bytesRead);

            System.out.println("Mensaje recibido: " + mensaje);

            // Enviar el mensaje a todos los clientes
            for (Socket c : clientes) {
                OutputStream out = c.getOutputStream();
                out.write(("Servidor recibió: " + mensaje).getBytes());
            }

            if (mensaje.equalsIgnoreCase("SALIR")) {
                cliente.close();
                clientes.remove(cliente);
                System.out.println("Cliente desconectado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
