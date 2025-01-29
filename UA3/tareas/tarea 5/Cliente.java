import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;

        try (Socket socket = new Socket(host, puerto);
             ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Introduce un número entero: ");
            int num = scanner.nextInt();

            if (num < 0) {
                System.out.println("Error: El número no puede ser negativo.");
                return;
            }

            // Enviar objeto al servidor
            Numeros numeros = new Numeros(num);
            salida.writeObject(numeros);

            // Recibir respuesta
            Object respuesta = entrada.readObject();

            if (respuesta instanceof String) {
                System.out.println("Respuesta del servidor: " + respuesta);
            } else if (respuesta instanceof Numeros) {
                Numeros resultado = (Numeros) respuesta;
                System.out.println("Número: " + resultado.getNumero());
                System.out.println("Cuadrado: " + resultado.getCuadrado());
                System.out.println("Cubo: " + resultado.getCubo());
            }

        } catch (UnknownHostException e) {
            System.out.println("No se pudo encontrar el servidor: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de comunicación con el servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer la respuesta del servidor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
