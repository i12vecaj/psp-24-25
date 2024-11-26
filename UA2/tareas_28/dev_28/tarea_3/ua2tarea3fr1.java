import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ua2tarea3fr1 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("tiene que tener un archivo de documento de texto");
            return;
        }

        long tComienzo = System.currentTimeMillis();

        for (String fileName : args) {
            int charCount = contarCaracteres(fileName);
            if (charCount != -1) {
                System.out.println("Archivo '" + fileName + "': " + charCount + " caracteres.");
            }
        }

        long tFin = System.currentTimeMillis();
        long tTotal = tFin - tComienzo;
        System.out.println("Tiempo de ejecución secuencial: " + tTotal + " ms.");
    }

    private static int contarCaracteres(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo '" + fileName + "': " + e.getMessage());
            return -1;
        }
        return count;
    }
}

