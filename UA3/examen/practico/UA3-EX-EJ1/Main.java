/* ¿Es más conveniente utilizar TCP o UDP en este caso? Explica tu respuesta.

En este caso, TCP es más conveniente porque garantiza que los mensajes lleguen completos, en orden y sin pérdidas.
Lo cual es esencial para un chat en tiempo real.


Explicación:
Modo de ejecución : Al ejecutar el programa, se pide si se quiere actuar como servidor ( S) o cliente ( C).

Servidor :
Se ejecuta en un bucle infinito esperando conexiones de clientes.
Usa ControladorClientes, hilos para manejar múltiples clientes.
Al recibir un mensaje, lo reenvía a todos los clientes conectados.

Cliente :
Se conecta al servidor y envía mensajes.
Puede recibir mensajes en tiempo real.
Para salir, envía "SALIR"y cierra la conexión.
Se pueden ejecutar varios clientes a la vez para poder hablar en el chat🚀

*/


import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    private static final int puerto = 10000;
    private static Set<ControladorClientes> clients = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Ejecutar como (S/C) ?");
        String mode = scanner.nextLine().trim().toUpperCase();

        if (mode.equals("S")) {
            abrirServidor();
        } else {
            abrirCliente();
        }
    }

    private static void abrirServidor() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);
            while (true) {
                Socket socket = serverSocket.accept();
                ControladorClientes controladorClientes = new ControladorClientes(socket);
                clients.add(controladorClientes);
                new Thread(controladorClientes).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void abrirCliente() {
        try (Socket socket = new Socket("localhost", puerto);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Ingrese su nombre: ");
            String name = scanner.nextLine();
            writer.println(name);

            Thread listener = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = reader.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Desconectado del servidor.");
                }
            });
            listener.start();

            String message;
            while (!(message = scanner.nextLine()).equalsIgnoreCase("SALIR")) {
                writer.println(message);
            }
            writer.println("SALIR");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ControladorClientes implements Runnable {
        private Socket socket;
        private PrintWriter writer;
        private String name;

        public ControladorClientes(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                this.writer = writer;
                this.name = reader.readLine();
                Comunicador(name + " se ha unido al chat.");

                String message;
                while ((message = reader.readLine()) != null) {
                    if ("SALIR".equalsIgnoreCase(message)) {
                        break;
                    }
                    System.out.println(name + ": " + message);
                    Comunicador(name + ": " + message);
                }

                socket.close();
                clients.remove(this);
                Comunicador(name + " ha salido del chat.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void Comunicador(String message) {
            for (ControladorClientes client : clients) {
                client.writer.println(message);
            }
        }
    }
}
// TCP es más conveniente porque garantiza la entrega, el orden y la integridad de los mensajes, lo cual es esencial en un chat.
//UDP, aunque más rápido, no asegura que los mensajes lleguen o lo hagan en orden, lo que puede causar pérdida
