import java.io.*;
import java.net.*;

public class Cliente1 {
    //Creamos las variables para el puerto y la direccion.
    private static final String direccion = "localhost";
    private static final int puerto = 6000;

    public static void main(String[] args) {
        //Creamos un socket para conectar con el servidor y enviar y recibir los datos.
        try (Socket socket = new Socket(direccion, puerto);
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            //Creamos un hilo para leer del socket y mostrarlo en pantalla.
            Thread lector = new Thread(() -> {
                try {
                    String mensaje;
                    System.out.println("Escribe un mensaje:");
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println("Cliente 1: " + mensaje);
                        if (mensaje.equals("salir")) {
                            System.out.println("Cerrando cliente 1...");
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //Creamos un hilo para escribir en el socket y enviarlo al otro cliente.
            lector.start();
            String mensaje;
            while ((mensaje = teclado.readLine()) != null) {
                salida.println(mensaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}