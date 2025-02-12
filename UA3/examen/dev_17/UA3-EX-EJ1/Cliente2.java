import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = "localhost";
        int puerto = 6000;

        System.out.println("Iniciamos el cliente 2");

        try (Socket cliente2 = new Socket(host, puerto);
            DataOutputStream salidaCliente2 = new DataOutputStream(cliente2.getOutputStream());
            DataInputStream entradaCliente2 = new DataInputStream(cliente2.getInputStream()))
        {
            String mensaje;
            while (true) {
                System.out.println("Introduzca el mensaje a enviar ('salir' para cerrar): ");
                mensaje = scanner.nextLine();

                salidaCliente2.writeUTF(mensaje);

                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }

                String respuestaServidor = entradaCliente2.readUTF();
                System.out.println("Respuesta del servidor: " + respuestaServidor);
            }

        } catch (IOException e) {
            System.err.println("Error en el cliente 2: " + e.getMessage());
        }

        scanner.close();
        System.out.println("Cliente 2 cerrado");
    }
}
