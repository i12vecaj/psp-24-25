import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServidorChat extends JFrame {
    private JTextArea areaTexto; // Área donde se muestran los mensajes
    private ServerSocket serverSocket; // Socket del servidor
    private ExecutorService poolClientes; // Hilos para manejar varios clientes
    private final List<PrintWriter> clientesConectados = new ArrayList<>(); // Lista de clientes

    // Constructor del servidor
    public ServidorChat() {
        setTitle("Servidor de Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false); // No se puede editar el área de texto
        add(new JScrollPane(areaTexto));

        // Cuando se cierre la ventana, se cierra el servidor
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                cerrarVentana();
            }
        });

        setVisible(true);

        // Iniciar el servidor en un hilo separado
        new Thread(this::iniciarServidor).start();
    }

    // Método para iniciar el servidor
    private void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(12345);
            poolClientes = Executors.newFixedThreadPool(5); // Máximo 5 clientes a la vez
            areaTexto.append("Servidor iniciado...\n");

            // Aceptar conexiones de clientes
            while (true) {
                Socket cliente = serverSocket.accept();
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

                synchronized (clientesConectados) {
                    clientesConectados.add(salida); // Agregar cliente a la lista
                }

                // Manejar al cliente en un nuevo hilo
                poolClientes.execute(new ManejadorCliente(cliente, salida));
                areaTexto.append("Cliente conectado: " + cliente.getInetAddress() + "\n");
            }
        } catch (IOException e) {
            areaTexto.append("Error en el servidor: " + e.getMessage() + "\n");
        }
    }

    // Método para cerrar el servidor
    private void cerrarVentana() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                areaTexto.append("Servidor cerrado.\n");
            }
        } catch (IOException e) {
            areaTexto.append("Error al cerrar el servidor: " + e.getMessage() + "\n");
        }
        System.exit(0);
    }

    // Clase interna para manejar a cada cliente
    private class ManejadorCliente implements Runnable {
        private final Socket socket;
        private final PrintWriter salida;
        private BufferedReader entrada;

        public ManejadorCliente(Socket socket, PrintWriter salida) {
            this.socket = socket;
            this.salida = salida;
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String mensaje;

                while ((mensaje = entrada.readLine()) != null) {
                    areaTexto.append(mensaje + "\n");
                    retransmitirMensaje(mensaje); // Enviar mensaje a todos los clientes
                }
            } catch (IOException e) {
                areaTexto.append("Conexión con cliente perdida.\n");
            } finally {
                try {
                    socket.close();
                    synchronized (clientesConectados) {
                        clientesConectados.remove(salida); // Eliminar cliente de la lista
                    }
                    areaTexto.append("Cliente desconectado.\n");
                } catch (IOException e) {
                    areaTexto.append("Error al cerrar la conexión.\n");
                }
            }
        }

        // Enviar un mensaje a todos los clientes
        private void retransmitirMensaje(String mensaje) {
            synchronized (clientesConectados) {
                for (PrintWriter cliente : clientesConectados) {
                    cliente.println(mensaje);
                }
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        new ServidorChat();
    }
}
