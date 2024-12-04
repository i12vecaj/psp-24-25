import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> archivos = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            String archivo;
            do {
                System.out.println("Introduce el nombre de tu archivo (para salir, usa '*'):");
                archivo = scanner.nextLine();
                if (!archivo.equals("*")) {
                    archivos.add(archivo);
                }
            } while (!archivo.equals("*"));
        }

        List<Thread> listaHilos = new ArrayList<>();

        for (String arch : archivos) {
            Hilo hilo = new Hilo(arch); // Cambio a la clase mejorada "Hilo"
            listaHilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : listaHilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + e.getMessage());
            }
        }

        System.out.println("¡Completado!");
    }
}
