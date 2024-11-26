import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Hilo extends Thread{
    private String nombreArchivo;
    private int numCaracteres;
    private FileReader lectura;

    public Hilo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    public void run() throws RuntimeException{
        int caract;
        try (FileReader lectura = new FileReader("src/"+nombreArchivo+".txt")){
            caract = lectura.read();
            while (caract != -1){
                numCaracteres++;
                caract = lectura.read();
            }
            System.out.println("Archivo: "+nombreArchivo+" Numero De Caracteres: "+numCaracteres);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
