//Código hecho por Alberto Mármol Bello para la asignatura de Servicios y Procesos a fecha 12/02/2025.
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat { 
    //Pongo un puerto en el que escucha el servidor.
    static final int PUERTO = 12345;
    //Aquí pongo la lista de clientes conectados.
    static Set<PrintWriter> clientes = new HashSet<>();
     
    public static void main(String[] args) {
        System.out.println("Servidor iniciado...");
        //Aquí creo el servidor en el puerto específico.
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) { 
            while (true) {
                //Luego acepto las nuevas conexiones de los clientes.
                new ManejadorCliente(serverSocket.accept()).start(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Esta es la clase interna para manejar cada cliente de forma independiente.
    public static class ManejadorCliente extends Thread {
        private Socket socket;
        private PrintWriter salida;
        private String nombre;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                //Añado un flujo de entrada para recibir mensajes del cliente.
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            ) {
                //Añado otro flujo de salida para enviar mensajes al cliente.
                salida = new PrintWriter(socket.getOutputStream(), true); 
                synchronized (clientes) {
                    //Agrego al cliente a la lista de clientes conectados.
                    clientes.add(salida); 
                }
                //Aquí pido al cliente que ingrese su nombre.
                salida.println("Ingresa tu nombre:"); 
                //Leo el nombre del cliente.
                nombre = entrada.readLine(); 
                System.out.println(nombre + " se ha conectado.");

                //Un bucle para recibir mensajes del cliente.
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    //Pongo el mensaje de "SALIR" cuando se finaliza la conexión.
                    if (mensaje.equalsIgnoreCase("SALIR")) { 
                        break;
                    }
                    //Muestro el mensaje en consola del servidor.
                    System.out.println(nombre + ": " + mensaje); 
                    //Y envio el mensaje a todos los clientes conectados.
                    reenviarMensaje(nombre + ": " + mensaje); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (nombre != null) {
                    //Cuando el cliente se va muestro el mensaje de desconexión.
                    System.out.println(nombre + " se ha desconectado."); 
                }
                synchronized (clientes) {
                    //Luego elimino el cliente de la lista.
                    clientes.remove(salida); 
                }
                try {
                    //Cierro la conexión del cliente.
                    socket.close(); 
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Este es el método para reenviar mensajes a todos los clientes que hay conectados.
    public static void reenviarMensaje(String mensaje) {
        synchronized (clientes) {
            for (PrintWriter cliente : clientes) {
                cliente.println(mensaje);
            }
        }
    }
}