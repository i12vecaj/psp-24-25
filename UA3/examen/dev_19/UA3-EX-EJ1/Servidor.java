import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static int PUERTO = 10000;
    private  static Set<Socket> clientes = Collections.synchronizedSet(new HashSet<>());
    //creamos una coleccion sincronizada
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                clientes.add(cliente);
                new HiloCliente(cliente).start();
                //creamos un hilo para cada cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class HiloCliente extends Thread {
        private Socket socket;
        private PrintWriter salida;
        private BufferedReader entrada;
        private String nombre;
        public HiloCliente(Socket socket) {
            this.socket = socket;
            //inicializamos el socket de cada cliente
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                salida.println("Ingresa tu nombre:");
                nombre = entrada.readLine();
                System.out.println(nombre + " se ha conectado.");

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    if (mensaje.equalsIgnoreCase("SALIR")) {
                        System.out.println(nombre + " se ha desconectado.");
                        break;
                    }
                    String mensajeFormateado = nombre + ": " + mensaje;
                    System.out.println(mensajeFormateado);
                    enviarTodos(mensajeFormateado);
                }
                //leemos la linea y la enviamos a todos
            } catch (IOException e) {
                System.out.println("Error con " + nombre);
            } finally {
                try {
                    //cerramos el socket y sacamos al cliente de la coleccion
                    socket.close();
                    clientes.remove(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //metodo de envio
        private void enviarTodos(String mensaje) {
            synchronized (clientes) {
                for (Socket cliente : clientes) {
                    try {
                        if (!cliente.isClosed()) {
                            new PrintWriter(cliente.getOutputStream(), true).println(mensaje);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
