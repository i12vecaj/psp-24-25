import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServidorChat extends JFrame {
    private JTextArea areaTexto;
    private ServerSocket serverSocket;
    private ExecutorService poolClientes;

    public ServidorChat() {
        // Configuración de la ventana
        setTitle("Servidor de Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el área de texto y agregarla a un panel con scroll
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto));

        // Mostrar la ventana gráfica
        setVisible(true);

        // Iniciar el servidor en un hilo separado
        new Thread(() -> {
            iniciarServidor();
        }).start();

        // Log para verificar que la interfaz se ha cargado
        System.out.println("Interfaz gráfica cargada.");
    }

    private void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(12345);
            poolClientes = Executors.newFixedThreadPool(5);  // Permitir hasta 5 clientes
            areaTexto.append("Servidor iniciado...\n");
            System.out.println("Servidor iniciado en el puerto 12345...");

            while (true) {
                Socket cliente = serverSocket.accept();
                areaTexto.append("Cliente conectado: " + cliente.getInetAddress() + "\n");
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                poolClientes.execute(new ManejadorCliente(cliente));
            }
        } catch (IOException e) {
            areaTexto.append("Error en el servidor: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private class ManejadorCliente implements Runnable {
        private Socket socket;
        private BufferedReader entrada;
        private PrintWriter salida;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    areaTexto.append("Cliente: " + mensaje + "\n");
                    salida.println("Servidor: Recibido -> " + mensaje);
                }
            } catch (IOException e) {
                areaTexto.append("Conexión con cliente perdida.\n");
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    areaTexto.append("Cliente desconectado.\n");
                    System.out.println("Cliente desconectado.");
                } catch (IOException e) {
                    areaTexto.append("Error al cerrar la conexión.\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Iniciando servidor...");
        new ServidorChat();
        System.out.println("Servidor iniciado.");
    }
}
