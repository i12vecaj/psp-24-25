import jdk.jshell.execution.JdiDefaultExecutionControl;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.Buffer;

public class ClientesChat {
    private static final String ipServidor = "localhost";
    private static final int puertoServidor = 10001;

    private Socket socketCli;
    private PrintWriter print;
    private BufferedReader en;

    private JFrame frameChat;
    private JTextArea zonaChat;
    private JTextField mensajeChat;
    private String nombre;

    public ClientesChat(){
        montarChats();
    }

    private void montarChats() {
        frameChat = new JFrame("Chate de Clientes");
        frameChat.setSize(400, 300);
        frameChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        zonaChat = new JTextArea();
        zonaChat.setEditable(false);
        frameChat.add(new JScrollPane(zonaChat), BorderLayout.CENTER);

        mensajeChat = new JTextField();
        frameChat.add(mensajeChat, BorderLayout.SOUTH);

        mensajeChat.addActionListener(e ->{
            String mensaje = mensajeChat.getText();
            if (!mensaje.isEmpty()){
                print.println(mensaje);
                mensajeChat.setText("");
            }
            if (mensaje.equalsIgnoreCase("Salir")){
                desconectarse();
            }
        });

        frameChat.setVisible(true);
        conectarAlServidor();
    }

    private void conectarAlServidor(){
        try {
            socketCli = new Socket(ipServidor, puertoServidor);
            print = new PrintWriter(socketCli.getOutputStream(), true);
            en = new BufferedReader(new InputStreamReader(socketCli.getInputStream()));

            nombre = JOptionPane.showInputDialog(frameChat, "Ingresa el nombre del cliente:");
            print.println(nombre);

            new Thread(() -> {
               try {
                   String mensaje;
                   while ((mensaje = en.readLine()) != null){
                       zonaChat.append(mensaje + "\n");
                   }
               } catch (IOException e){
                   e.printStackTrace();
               }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frameChat, "No se pudo conectar al servidor, comprueba que este este iniciado.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void desconectarse() {
        try {
            print.println("Salir");
            socketCli.close();
            System.exit(0);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String cuantosClientes = JOptionPane.showInputDialog("Cuantos clientes quieres conectar?");
        try {
            int numClientes = Integer.parseInt(cuantosClientes);
            if (numClientes > 0) {
                for (int i = 0; i < numClientes; i++) {
                    SwingUtilities.invokeLater(ClientesChat::new);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar al menos un cliente.");
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Dato invalido, debes introducir un numero entero.");
        }
    }
}
