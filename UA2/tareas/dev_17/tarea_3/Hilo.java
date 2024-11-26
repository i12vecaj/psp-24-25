import java.io.FileReader;

public class Hilo extends Thread {
    private String nombreArchivo;
    private int numeroCaracteres;

    public Hilo(String nombreArchivo) { // Constructor que recibe el nombre del archivo
        this.nombreArchivo = nombreArchivo;
        setName(nombreArchivo); // Asignamos el nombre al hilo
    }

    @Override
    public void run() {
        int caracter;

        try (FileReader lector = new FileReader("src/"+nombreArchivo)) { // Abre el archivo
            caracter = lector.read(); // Leemos el primer carácter

            while (caracter != -1) { // Mientras no llegue al final
                numeroCaracteres++; // Incrementamos el contador
                caracter = lector.read(); // Leemos el siguiente carácter
            }

            System.out.println("Archivo " + nombreArchivo + " tiene " + numeroCaracteres + " caracteres.");// Imprime el resultado
        } catch (Exception e) {
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
