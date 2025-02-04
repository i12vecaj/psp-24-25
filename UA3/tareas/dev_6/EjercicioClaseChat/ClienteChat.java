import javax.swing.*;
import java.io.*;
import java.net.*;

public class ClienteChat extends JFrame {
    private JTextField campoEntrada;
    private JTextArea areaTexto;
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public ClienteChat() {
        setTitle("Cliente de Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campoEntrada = new JTextField();
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        add(new JScrollPane(areaTexto), "Center");
        add(campoEntrada, "South");

        campoEntrada.addActionListener(e -> enviarMensaje(campoEntrada.getText()));

        conectarAlServidor();
    }

    private void conectarAlServidor() {
        try {
            socket = new Socket("localhost", 12345);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            areaTexto.append("Conectado al servidor.\n");

            // Hilo para recibir mensajes del servidor
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

    private void enviarMensaje(String mensaje) {
        if (mensaje.isEmpty() || salida == null) return;
        salida.println(mensaje);
        campoEntrada.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteChat().setVisible(true));
    }
}
