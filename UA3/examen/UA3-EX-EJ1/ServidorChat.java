import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.*;

public class ServidorChat {
    static final int Puerto = 10001;
    private static Set<PrintWriter> clienteChat = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("El servidor ha sido iniciado en el puerto: " + Puerto);

        try (ServerSocket socketServer = new ServerSocket(Puerto)){
            while (true){
                Socket socketCliente = socketServer.accept();
                new UsoCliente(socketCliente).start();
            }
        } catch (IOException e) {
        e.printStackTrace();}
    }

    private static class UsoCliente extends Thread {
        private Socket socketCli;
        private PrintWriter print;
        private String nombreCliente;

        public UsoCliente(Socket socketCli){
            this.socketCli = socketCli;
        }

        public void run(){
            try (BufferedReader en = new BufferedReader(new InputStreamReader(socketCli.getInputStream()))) {
                print = new PrintWriter(socketCli.getOutputStream(), true);
                synchronized (clienteChat){
                    clienteChat.add(print);
                }

                print.println("Ingresa tu nombre:");
                nombreCliente = en.readLine();
                System.out.println("el cliente "+ nombreCliente +" se ha conectado.");

                String mensaje;
                while ((mensaje = en.readLine()) != null){
                    if (mensaje.equalsIgnoreCase("Salir")){
                        break;
                    }
                    System.out.println(nombreCliente + ": "+ mensaje);
                    synchronized (clienteChat){
                        for (PrintWriter escritor : clienteChat){
                            escritor.println(nombreCliente +": "+ mensaje);
                        }
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if (clienteChat != null){
                    System.out.println("El cliente "+nombreCliente + " se ha desconectado.");
                }
                synchronized (clienteChat){
                    clienteChat.remove(print);
                }
                try {
                    socketCli.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/*
Para este ejercicio de chat con clientes y un servidor es mejor la configuracion de TCP:

Explicacion de porque TCP y no UDP:
1. Confiable: TCP asegura la entrega de los mensaje cosa que UDP no.
2. Conexion Persistente: TCP mantiene una conexión estable entre el cliente y el servidor, lo que facilita el manejo de clientes.
3. Manejo de multiples clientes: Con TCP, el servidor puede aceptar conexiones de varios clientes usando sockets y manejar cada cliente en un hilo.
4. Control de flujo: TCP ajusta automaticamente la velocidad de transmisión para evitar saturar la red.
*/
