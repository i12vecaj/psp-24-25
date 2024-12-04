package tarea_3.mejoras;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConsumidorMejoras extends Thread{
    private String rutaArchivo;
    private int contador;

    public ConsumidorMejoras(String rutaArchivo){
        this.rutaArchivo=rutaArchivo;
        setName(rutaArchivo);
    }

    @Override
    public void run() {
        int caracter;
        // introducimos la ruta del archivo que vamos a leer usando Filereader
        //ACLARACION: no se por que cuando pongo otra ruta que no sea la ruta absoluta hacia el archivo txt me dice que el archivo no existe.

        try(FileReader lectura = new FileReader("/home/manuel/Escritorio/DAM/psp-24-25/UA2/tareas/dev_30/src/tarea_3/Archivos/"+rutaArchivo)) {

            //capturamos un caracter del archivo .txt
            caracter =lectura.read();

            //recorremos el archivos y aumentamos el valor del contador en función de los caracteres del txt
            while (caracter!=-1){
                contador++;
                caracter = lectura.read();
            }
            System.out.println("El numero de caracteres del archivo: "+rutaArchivo +" es de: "+contador +" de caracteres.");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
