import java.io.FileReader;
import java.io.IOException;

public class Hilo extends Thread {
    private final String nombreArchivo;
    private int numCaracteres;

    public Hilo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.numCaracteres = 0;
    }

    @Override
    public void run() {
        try (FileReader revisionDeCaracteres = new FileReader("src/" + nombreArchivo + ".txt")) {
            int caracter = revisionDeCaracteres.read();
            while (caracter != -1) {
                numCaracteres++;
                caracter = revisionDeCaracteres.read();
            }
            System.out.println("El archivo \"" + nombreArchivo + "\" tiene esta cantidad de caracteres: " + numCaracteres);
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo \"" + nombreArchivo + "\": " + e.getMessage());
        }
    }
}
