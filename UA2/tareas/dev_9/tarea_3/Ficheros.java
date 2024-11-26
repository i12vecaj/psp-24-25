package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ficheros extends Thread{
    private FileReader fr;
    private String nombre;
    private int numero_caracteres;

    public Ficheros(String nombre) {
        this.nombre = nombre;
        setName(nombre);
    }
    public void run() throws RuntimeException{

        int caract;
        try (FileReader fr = new FileReader(nombre + ".txt")) {
            caract = fr.read();
            while (caract != -1) {
                numero_caracteres++;
                caract = fr.read();
            }
            // Mostramos los caracteres de cada archivo
            System.out.println("En el archivo de texto " + nombre +
                    " hay " + numero_caracteres + " caracteres");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo " + nombre + ": " + e.getMessage());
        }

    }
}
