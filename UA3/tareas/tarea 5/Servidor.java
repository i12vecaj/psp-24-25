import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexión en el puerto " + puerto);

            while (true) {
                try (Socket socket = servidor.accept();
                     ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream())) {

                    System.out.println("Cliente conectado...");

                    // Recibir objeto del cliente
                    Numeros numeros = (Numeros) entrada.readObject();
                    int num = numeros.getNumero();

                    if (num < 0) {
                        System.out.println("Número negativo recibido. Enviando error...");
                        salida.writeObject("Error: El número no puede ser negativo.");
                    } else {
                        // Calcular cuadrado y cubo
                        numeros.setCuadrado((long) num * num);
                        numeros.setCubo((long) num * num * num);

                        // Enviar objeto modificado al cliente
                        salida.writeObject(numeros);
                        System.out.println("El número recibido es: " + numeros.getNumero());
                        System.out.println("Resultados enviados al cliente.");
                        System.out.println("El resultado del cuadrado enviado al cliente es: " + numeros.getCuadrado());
                        System.out.println("El resultado del cubo enviado al cliente es: " + numeros.getCubo());
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Error al leer el objeto: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
