import java.io.*;
import java.net.Socket;

public class Cliente implements Runnable {

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private String nombreUsuario;
    private boolean bucle = true;

    public Cliente(Socket s, String nombreUsuario) {
        this.socket = s;
        this.nombreUsuario = nombreUsuario;

        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            salida.writeUTF(nombreUsuario);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (bucle) {
            try {
                String mensaje = entrada.readUTF();
                System.out.println(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int Puerto = 6000; // Puerto
        Socket s = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean bucle = true;

        System.out.println("Escribe tu nombre de usuario: ");
        String nombreUsuario = br.readLine();

        if (nombreUsuario.trim().isEmpty()) {
            System.out.println("El nombre de usuario no está disponible");
            return;
        }

        try {
            s = new Socket(Host, Puerto);
            Cliente c = new Cliente(s, nombreUsuario);
            Thread t = new Thread(c);
            t.start();

            while (bucle) {
                String mensaje = br.readLine();
                if (mensaje.equalsIgnoreCase("Salir")) {
                    bucle = false;
                    c.salida.writeUTF("Salir");
                } else {
                    c.salida.writeUTF(mensaje);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}