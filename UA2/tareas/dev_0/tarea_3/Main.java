import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> archivos = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            String archivo;
            do {
                System.out.println("introduce el nombre de tu archivo para salir usa esto(*):");
                archivo = scanner.nextLine();
                if (!archivo.equals("ç")) {
                    archivos.add(archivo);
                }
            } while (!archivo.equals("*"));
        }

      List<Thread> listaHilos = new ArrayList<>();

        for (String arch : archivos) {
            HILO hilo = new HILO(arch);
            listaHilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : listaHilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println( "Error:" + e.getMessage());
            }
        }

        System.out.println("completado!");
    }
}
