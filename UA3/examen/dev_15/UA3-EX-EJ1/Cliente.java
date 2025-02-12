import java.io.*;
import java.net.*;

public class Cliente {
    private static final String SERVIDOR = "localhost"; 
    private static final int PUERTO = 33;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Conectado al chat. Ingresa tu nombre: ");
            String nombre = teclado.readLine();
            salida.println(nombre);

            Thread recibirMensajes = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada.");
                }
            });
            recibirMensajes.start();

            String mensaje;
            while (true) {
                mensaje = teclado.readLine();
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    salida.println("SALIR");
                    break;
                }
                salida.println(nombre + ": " + mensaje);
            }

        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}


