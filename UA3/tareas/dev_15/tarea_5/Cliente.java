import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        //DATOS DEL SERVIDOR
        String SERVIDOR = "localhost";
        int PUERTO = 33;

        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Introduce un número entero positivo: ");
            int numero = scanner.nextInt();

            if (numero < 0) {
                System.err.println("El número debe ser mayor o igual a 0.");
                return;
            }

            // Creamos y enviamos el objeto al servidor
            Numeros numeros = new Numeros(numero);
            salida.writeObject(numeros);

            // Recibimos el objeto con cálculos realizados por el servidor
            Numeros numerosProcesados = (Numeros) entrada.readObject();
            System.out.println("Respuesta del servidor: " + numerosProcesados);

        } catch (IOException e) {
            System.err.println("Error de conexión con el servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error al recibir el objeto del servidor.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
}
