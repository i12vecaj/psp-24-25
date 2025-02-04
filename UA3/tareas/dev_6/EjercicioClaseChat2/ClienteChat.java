import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClienteChat extends JFrame {
    private JTextField campoEntrada; // Campo de texto para escribir mensajes
    private JTextArea areaTexto; // Área donde se muestran los mensajes
    private JButton botonEnviar; // Botón para enviar mensajes
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private String nombreCliente;

    // Constructor del cliente
    public ClienteChat(String nombreCliente) {
        this.nombreCliente = nombreCliente;

        setTitle("Cliente de Chat - " + nombreCliente);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campoEntrada = new JTextField();
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        botonEnviar = new JButton("ENVIAR MENSAJE");

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.X_AXIS));
        panelInferior.add(campoEntrada);
        panelInferior.add(botonEnviar);

        add(new JScrollPane(areaTexto), "Center");
        add(panelInferior, "South");

        botonEnviar.addActionListener(e -> enviarMensaje());
        campoEntrada.addActionListener(e -> enviarMensaje());

        // Cuando se cierre la ventana, se cierra la conexión
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                cerrarVentana();
            }
        });

        conectarAlServidor();
        setVisible(true);
    }

    // Método para conectar al servidor
    private void conectarAlServidor() {
        try {
            socket = new Socket("localhost", 12345);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            areaTexto.append("Conectado al servidor.\n");

            // Hilo para recibir mensajes
            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        areaTexto.append(mensaje + "\n");
                    }
                } catch (IOException e) {
                    areaTexto.append("Conexión con el servidor perdida.\n");
                }
            }).start();
        } catch (IOException e) {
            areaTexto.append("No se pudo conectar al servidor.\n");
        }
    }

    // Método para enviar mensajes
    private void enviarMensaje() {
        String mensaje = campoEntrada.getText().trim();
        if (!mensaje.isEmpty() && salida != null) {
            salida.println(nombreCliente + ": " + mensaje);
            campoEntrada.setText("");
        }
    }

    // Método para cerrar el cliente
    private void cerrarVentana() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                areaTexto.append("Cliente desconectado.\n");
            }
        } catch (IOException e) {
            areaTexto.append("Error al cerrar el cliente: " + e.getMessage() + "\n");
        }
        System.exit(0);
    }

    // Método principal
    public static void main(String[] args) {
        String nombreCliente = JOptionPane.showInputDialog(null, "Introduce el nombre del cliente:");
        if (nombreCliente != null && !nombreCliente.trim().isEmpty()) {
            new ClienteChat(nombreCliente.trim());
        }
    }
}
