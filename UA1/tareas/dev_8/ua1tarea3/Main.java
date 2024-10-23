import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> listaArgumentos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese argumentos (presione Enter después de cada argumento, y escriba '*' para terminar):");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("*")) {
                break;
            }
            listaArgumentos.add(input);
        }

        // Crear y ejecutar el hilo
        Hilos hilo = new Hilos(listaArgumentos);
        Thread thread = new Thread(hilo);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
        }

        int codigoSalida = hilo.getCodigoSalida();
        System.out.println("Código de salida: " + codigoSalida);

        switch (codigoSalida) {
            case 1:
                System.out.println("Número de argumentos es menor que 1");
                break;
            case 2:
                System.out.println("Argumento es una cadena");
                break;
            case 3:
                System.out.println("Argumento es un número entero negativo");
                break;
            case 0:
                System.out.println("Argumento es un número entero no negativo");
                break;
            default:
                System.out.println("Código de salida desconocido");
        }

        scanner.close();
    }
}
