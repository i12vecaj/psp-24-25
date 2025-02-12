import java.io.IOException;
import java.net.*;
import java.util.logging.*;


public class ChatServer2 {
 // El método principal del Servidor de Chat
    public static void main(String args[]) throws IOException {
 // Declaración de variables
        ServerSocket ss;
        System.out.print("Arrancando.... ");
        try {
            ss = new ServerSocket(10578);
            System.out.println("\\t[OK]");
            int idSession = 0;
            while (true) {
                // Aceptar conexiones entrantes
                Socket socket;
                socket = ss.accept();
                System.out.println("Conexion nueva entrando "+socket);
                // En construccion.....
                idSession++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ChatServer2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}