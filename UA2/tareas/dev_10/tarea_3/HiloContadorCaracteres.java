import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HiloContadorCaracteres implements Runnable {
    private String nombreArchivoALeer;
    private int contador;
    HiloContadorCaracteres(String nombreArchivoALeer){
        this.nombreArchivoALeer = nombreArchivoALeer;
        this.contador = 0;
    }

    @Override
    public void run() {
        FileReader lector = null;

        try {
            lector = new FileReader(nombreArchivoALeer); // instanciando el FileReader (un lector)

            int caracter = lector.read();

            while (caracter != -1)
            {
                contador++;
                caracter = lector.read(); // leyendo los caracteres
            }
            System.out.println(contador);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (lector != null){
                try {
                    lector.close(); //Cerrando el lector por que si lo dejas abierto consume sin hacer nada.
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
