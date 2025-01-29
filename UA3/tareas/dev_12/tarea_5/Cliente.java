package tarea5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Introduce un número entero: ");
            int numero = scanner.nextInt();

            // Verificar que el número sea válido (mayor o igual que 0)
            if (numero < 0) {
                System.out.println("Número no válido (debe ser mayor o igual a 0).");
                return;
            }

            // Crear el objeto Numeros
            Numeros numeros = new Numeros(numero);

            // Conectar al servidor
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Conectando al servidor...");

            // Crear flujos de entrada y salida
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Enviar el objeto Numeros al servidor
            output.writeObject(numeros);
            output.flush();

            // Recibir el objeto con los resultados
            Numeros resultado = (Numeros) input.readObject();
            System.out.println("Respuesta del servidor: " + resultado);

            // Cerrar conexiones
            input.close();
            output.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error de conexión o procesamiento: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
