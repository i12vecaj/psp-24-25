import java.io.*;
import java.net.*;

public class ClienteChat {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            new Thread(new LeerMensaje(socket)).start();
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Ingresa tu nombre: ");
            String nombre = teclado.readLine();
            System.out.println("Introduzca un mensaje: ");
            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    salida.writeUTF("SALIR");
                    break;
                }
                salida.writeUTF(nombre + ": " + mensaje);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class LeerMensaje implements Runnable {
    private Socket socket;

    public LeerMensaje(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String mensaje;
            while ((mensaje = entrada.readUTF()) != null) {
                System.out.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
