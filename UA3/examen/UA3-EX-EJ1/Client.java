import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private PrintWriter out;

    public Client() {
        createGUI();
        connectToServer();
    }

    //creacion de interfaz de usuario. se implementa la caja del chat, el panel de texto y el  botón enviar.

    private void createGUI() {
        setTitle("Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());
    }

    private void connectToServer() {
        try {           /*método sencillo que conecta al servidor (en este caso localhost, por
                                    motivos de simulación), o notifica al usuario en caso contrario.*/
            Socket socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(new MessageReader(socket)).start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to server!");
        }
    }

    private void sendMessage() {
        String message = "(Client 0) " + inputField.getText();
        if (!message.isEmpty()) {
            out.println(message);
            inputField.setText("");
        }
    }

    private class MessageReader implements Runnable {
        private BufferedReader in;

        public MessageReader(Socket socket) throws IOException {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            try {                   /* esta secuencia de código muestra el mensaje en la pantalla
                                    los mensajes con chatArea.append(finalMessage + "\n").
                                    asegura que los mensajes se muestren de manera secuencial con
                                    un ENTER presionado.*/
                String message;
                while ((message = in.readLine()) != null) {
                    String finalMessage = message;
                    SwingUtilities.invokeLater(() ->
                            chatArea.append(finalMessage + "\n")
                    );
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(Client.this, "Connection lost!");
            }
        }
    }

    //ejecucion del cliente con SwingUtilities

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
    }
}
