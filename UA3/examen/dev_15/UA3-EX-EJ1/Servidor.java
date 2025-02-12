import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 33;
    private static Set<PrintWriter> clientes = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Servidor iniciado en el puerto " + PUERTO);
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            while (true) {
                Socket cliente = servidor.accept();
                new ManejadorCliente(cliente).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ManejadorCliente extends Thread {
        private Socket socket;
        private PrintWriter salida;
        private String nombre;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        public void servidor() {
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                salida = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientes) {
                    clientes.add(salida);
                }

                salida.println("Ingresa el nombre del cliente:");
                nombre = entrada.readLine();
                System.out.println(nombre + " se ha conectado al servidor ...");
                enviarmensajeatodos(nombre + " se ha unido al chat....");

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    if (mensaje.equalsIgnoreCase("SALIR")) {
                        break;
                    }
                    System.out.println(nombre + ": " + mensaje);
                    enviarmensajeatodos(nombre + ": " + mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (nombre != null) {
                    System.out.println(nombre + " ha salido.");
                    enviarmensajeatodos(nombre + " ha salido del chat.");
                }
                synchronized (clientes) {
                    clientes.remove(salida);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void enviarmensajeatodos(String mensaje) {
            synchronized (clientes) {
                for (PrintWriter cliente : clientes) {
                    cliente.println(mensaje);
                }
            }
        }
    }
}