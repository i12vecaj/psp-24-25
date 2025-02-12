import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = "localhost";
        int puerto = 6000;

        System.out.println("Iniciamos el cliente");

        try (Socket cliente1 = new Socket(host, puerto);
            DataOutputStream salidaCliente1 = new DataOutputStream(cliente1.getOutputStream());
            DataInputStream entradaCliente1 = new DataInputStream(cliente1.getInputStream()))
        {
            String mensaje;
            while (true) {
                System.out.println("Introduzca el mensaje a enviar ('salir' para cerrar): ");
                mensaje = scanner.nextLine();

                salidaCliente1.writeUTF(mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }

                String respuestaServidor = entradaCliente1.readUTF();
                System.out.println("Respuesta del servidor: " + respuestaServidor);
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente 1: " + e.getMessage());
        }

        scanner.close();
        System.out.println("Cliente 1 cerrado");
    }
}
