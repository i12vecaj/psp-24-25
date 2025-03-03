import java.io.*;
import java.net.*;

public class NotificacionCliente {
    private Socket socket;
    private PrintWriter salida;

    public NotificacionCliente(String host, int port) {
        try {
            socket = new Socket(host, port);
            salida = new PrintWriter(socket.getOutputStream(), true);
            new ServerListener(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ServerListener extends Thread {
        private Socket socket;

        public ServerListener(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String notificacion;
                while ((notificacion = in.readLine()) != null) {
                    System.out.println("Notificacion recibida: " + notificacion);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void enviarNotificacion(String notificacion) {
        salida.println(notificacion);
    }

    public static void main(String[] args) {
        NotificacionCliente cliente = new NotificacionCliente("localhost", 10200);
        // Enviar una notificacion de prueba
        cliente.enviarNotificacion("Esta es una prueba de notificación.");
    }
}