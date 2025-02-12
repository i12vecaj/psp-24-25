import java.io.*;
import java.net.*;
import java.util.*;
// Nombre: Alejandro Sanchez Quesada
// Respuesta a mi solucion de lo que yo creo  en este caso mejor usa el TCP:
// Ya que tienen un orden de los mensajes y te garantiza que te va llegar los mensaje , porque en el caso de udp no tiene un orden de mensajes y encima lo mas importante que no llega con seguridad el mensaje.
public class ChatServidor {
    public static void main(String[] args) throws IOException {
        int puerto = 12345;
        ServerSocket servidor = new ServerSocket(puerto);  // este es el socket del servidor
        System.out.println("el servido a inciado ya en el puerto " + puerto);

        Set<PrintWriter> clientes = new HashSet<>();  // sirve para que pueda manejar diferentes clientes

        try {
            while (true) {
                Socket cliente = servidor.accept();  // aqui acepto la conexion del cliente
                System.out.println("el cliente se a conectado " + cliente.getInetAddress());


                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);


                synchronized (clientes) { // añado los clientes
                    clientes.add(salida);
                }

                // aqui en mi caso utilizo el hilo para manejar la conexion del cliente
                new Thread(() -> {
                    try {
                        String mensaje;
                        while ((mensaje = entrada.readLine()) != null) {
                            if (mensaje.equalsIgnoreCase("salir")) {
                                break;
                            }
                            System.out.println("mensaje recibido: " + mensaje);


                            synchronized (clientes) { // aqui reenvio el mensaje a todos los clientes
                                for (PrintWriter pw : clientes) {
                                    pw.println(mensaje);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            cliente.close();  // cierro el socket y la conexion con el cliente
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        synchronized (clientes) {
                            clientes.remove(salida);
                        }
                    }
                }).start();
            }
        } finally {
            servidor.close();
        }
    }
}
