package org.example;

import java.io.FileReader;

public class Hilo extends Thread {
    public FileReader reader;
    public String archivo;
    public int caracteres;

    public Hilo(String archivo) {
        this.archivo = archivo;
    }

    public void run() { //Esta función es igual que la de ejecución secuencial
        try{
            reader = new FileReader( archivo);
            while(reader.read() != -1){
                caracteres++;
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("El archivo: " + archivo + " tiene caracteres: " + caracteres);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
