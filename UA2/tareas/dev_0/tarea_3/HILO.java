import java.io.FileReader;
import java.io.IOException;

public class HILO extends Thread {
    private String nombreArchivo;
    private int numCaracteres;

    public HILO(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.numCaracteres = 0;
    }

    @Override
    public void run() {
        try (FileReader revisiondecataracteres= new FileReader("src/" + nombreArchivo + ".txt")) {
            int caracteres= revisiondecataracteres.read();
            while (caracteres != -1) {
                numCaracteres++;
                caracteres = revisiondecataracteres.read();
            }
            System.out.println("EL archivo: " + nombreArchivo + " Esta cantidad de caracteres:" + numCaracteres);
        } catch (IOException e) {
            System.err.println("Error con el  archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
