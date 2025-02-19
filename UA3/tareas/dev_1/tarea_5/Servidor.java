import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado. Esperando conexiones...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

                    //recibir el objeto Numeros del cliente
                    Numeros numeros = (Numeros) ois.readObject();
                    System.out.println("Número recibido: " + numeros.getNumero());

                    //calcular cuadrado y cubo
                    int numero = numeros.getNumero();
                    numeros.setCuadrado((long) numero * numero);
                    numeros.setCubo((long) numero * numero * numero);

                    // enviar el objeto actualizado al cliente
                    oos.writeObject(numeros);
                    System.out.println("Objeto enviado: " + numeros);

                } catch (ClassNotFoundException e) {
                    System.err.println("Error: Clase no encontrada.");
                } catch (IOException e) {
                    System.err.println("Error de E/S: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
