import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Introduce un número entero (mayor o igual a 0): ");
            int numero = scanner.nextInt();

            //validar que el número sea mayor o igual a 0
            if (numero < 0) {
                System.err.println("Error: El número debe ser mayor o igual a 0.");
                return;
            }

            //crear el objeto Numeros
            Numeros numeros = new Numeros(numero);

            try (Socket socket = new Socket(SERVIDOR_IP, PUERTO);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                //enviar el objeto al servidor
                oos.writeObject(numeros);
                System.out.println("Objeto enviado: " + numeros);

                //recibir el objeto actualizado del servidor
                Numeros respuesta = (Numeros) ois.readObject();
                System.out.println("Objeto recibido: " + respuesta);

            } catch (ConnectException e) {
                System.err.println("Error: El servidor no está iniciado o no se puede conectar.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: Clase no encontrada.");
            } catch (IOException e) {
                System.err.println("Error de E/S: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Error: Entrada inválida.");
        }
    }
}
