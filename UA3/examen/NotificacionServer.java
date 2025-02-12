import java.io.*;
import java.net.*;
import java.util.*;

public class NotificacionServer {
    private static Set<PrintWriter> clientes = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Notificaciones activadas...");
        try (ServerSocket serverSocket = new ServerSocket(10200)) {
            while (true) {
                new ClienteHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler extends Thread {
        private Socket socket;
        private PrintWriter salida;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                InputStream input = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                salida = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientes) {
                    clientes.add(salida);
                }

                String notificacion;
                while ((notificacion = in.readLine()) != null) {
                    System.out.println("Notificacion recibida: " + notificacion);
                    enviarNotificacion("Nueva alerta: " + notificacion);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientes) {
                    clientes.remove(salida);
                }
            }
        }

        private void enviarNotificacion(String mensaje) {
            synchronized (clientes) {
                for (PrintWriter cliente : clientes) {
                    cliente.println(mensaje);
                }
            }
        }
    }
}