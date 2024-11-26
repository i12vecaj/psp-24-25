import java.io.FileReader;
import java.io.IOException;

public class Ficheros extends Thread {
    private final String nombre;
    private int numero_caracteres;

    public Ficheros(String nombre) {
        this.nombre = nombre;
        setName(nombre);
    }

    @Override
    public void run() {
        try (FileReader fr = new FileReader(nombre)) {
            int caract;
            while ((caract = fr.read()) != -1) {
                numero_caracteres++;
            }
            System.out.println("En el archivo de texto " + nombre + " hay " + numero_caracteres + " caracteres");
        } catch (IOException e) {
            System.err.println("Error procesando archivo " + nombre + ": " + e.getMessage());
        }
    }
}