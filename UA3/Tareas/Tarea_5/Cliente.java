import com.sun.management.HotSpotDiagnosticMXBean;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
  public static void main(String[] args) {
    String Host = "localhost";
    int puerto = 12345;

    try (Socket socket = new Socket(Host, puerto);
         ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
         ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
         Scanner scanner = new Scanner(System.in)) {

      System.out.print("Introduce un número entero positivo: ");
      int numero;

      while (true) {
        try {
          numero = Integer.parseInt(scanner.nextLine());
          if (numero < 0) {
            throw new NumberFormatException("El número debe ser positivo.");
          }
          break;
        } catch (NumberFormatException e) {
          System.out.print("Entrada inválida. Introduce un número entero positivo: ");
        }
      }

      Numeros num = new Numeros(numero);
      out.writeObject(num);

      num = (Numeros) in.readObject();
      System.out.println("Número: " + num.getNumero());
      System.out.println("Cuadrado: " + num.getCuadrado());
      System.out.println("Cubo: " + num.getCubo());

    } catch (UnknownHostException e) {
      System.out.println("No se pudo conectar al servidor: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("Error en la comunicación con el servidor: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println("Error al recibir datos: " + e.getMessage());
    }
  }
}

