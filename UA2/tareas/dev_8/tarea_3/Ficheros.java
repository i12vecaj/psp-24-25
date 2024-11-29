import java.io.FileReader;
import java.io.IOException;

public class Ficheros extends Thread {
    private final String nombreArchivo;

    public Ficheros(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {
        System.out.println("Procesando archivo en hilo: " + nombreArchivo);
        try (FileReader fr = new FileReader(nombreArchivo)) {
            int contadorCaracteres = 0;
            int caracter;
            while ((caracter = fr.read()) != -1) {
                contadorCaracteres++;
            }
            System.out.println("Archivo: " + nombreArchivo + " - Total caracteres: " + contadorCaracteres);
        } catch (IOException e) {
            System.err.println("Error procesando archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
