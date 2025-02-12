import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    //Creamos las variables para el puerto y creamos una lista con el flujo de clientes
    private static int puerto = 6000;
    private static List<OutputStream> flujoClientes = new ArrayList<>();
    public static void main(String[] args) {

        //Iniciamos el Servidor TCP en el puerto especificado y lo ponemos ne escucha a la espera de que se conecte.
        System.out.println("Servidor TCP iniciado en escucha...");
        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            while(true){
            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Conexión desde: " + clienteSocket.getInetAddress().getHostAddress());
            new ManejadorCliente(clienteSocket).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ManejadorCliente extends Thread {
        private Socket socket;
        private OutputStream flujoSalida;
        private BufferedReader flujoEntrada;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                InputStream flujoEntradaSocket = socket.getInputStream();
                flujoSalida = socket.getOutputStream();
                flujoEntrada = new BufferedReader(new InputStreamReader(flujoEntradaSocket));

                synchronized (flujoClientes) {
                    flujoClientes.add(flujoSalida);
                }

                String mensaje;
                while ((mensaje = flujoEntrada.readLine()) != null) {
                    System.out.println("Mensaje recibido: " + mensaje);
                    synchronized (flujoClientes) {
                        for (OutputStream flujo : flujoClientes) {
                            flujo.write((mensaje + "\n").getBytes());
                            flujo.flush();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (flujoClientes) {
                    flujoClientes.remove(flujoSalida);
                }
            }
        }
    }
}
