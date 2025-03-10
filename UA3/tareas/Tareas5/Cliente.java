import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un numero entero: ");
        int numero;

        try {
            numero = scanner.nextInt();
            if (numero < 0) {
                System.out.println("El numero debe ser mayor o igual a 0.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Entrada no valida. Debe ser un numero entero.");
            return;
        }

        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Numeros numeros = new Numeros(numero);
            out.writeObject(numeros);
            Numeros resultado = (Numeros) in.readObject();

            if (resultado == null) {
                System.out.println("El servidor recibio un numero negativo.");
            } else {
                System.out.println("Numero: " + resultado.getNumero());
                System.out.println("Cuadrado: " + resultado.getCuadrado());
                System.out.println("Cubo: " + resultado.getCubo());
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error al recibir el objeto del servidor: " + e.getMessage());
        }
    }
}