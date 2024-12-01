package Mejoras;
import java.io.FileReader;
import java.io.IOException;

public class HiloContadorCaracteres implements Runnable {
    private String nombreArchivoALeer;
    private int contadorCaracteres;

    // Constructor
    public HiloContadorCaracteres(String nombreArchivoALeer) {
        this.nombreArchivoALeer = nombreArchivoALeer;
        this.contadorCaracteres = 0;
    }

    @Override
    public void run() {
        try (FileReader lector = new FileReader(nombreArchivoALeer)) {
            int caracter;
            while ((caracter = lector.read()) != -1) {
                contadorCaracteres++; // Incrementa el contador por cada caracter leído
            }
            System.out.println("Número total de caracteres en el archivo: " + contadorCaracteres);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public int getContadorCaracteres() {
        return contadorCaracteres;
    }
}
