package org.example;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> ficheros = new ArrayList<String>();
        ArrayList<Hilo> hilos = new ArrayList<Hilo>();
        String nombreArchivo;
        Scanner escaner = new Scanner(System.in);


        do{
            System.out.println("Introduce el nombre el archivo(para finalizar el programa introduce '*'): ");
            nombreArchivo = escaner.nextLine(); //Leemos el nombre el archivo
            if(!nombreArchivo.equals("*")){
                ficheros.add("src/main/java/org/example/"+nombreArchivo); //He tenido que especificarle la ruta para que funcione correctamene, en case de que no funcion, prueba a borrarla y dejar solo el nombre del archivo
            }
        }while(!nombreArchivo.equals("*"));
        System.out.println("Ejecución secuencial");
        for(String archivo: ficheros){ //Iniciamos la ejecución secuencial de la funcion
            caracteresFichero(archivo);
        }


        System.out.println("Ejecución con hilos");
        for(String archivo: ficheros){
            hilos.add(new Hilo(archivo)); //Creamos los hilos y los añadimos al arraylist de hilos
            hilos.getLast().start(); //Iniciamos el último hilo que es el que acabamos de añadir
        }


    }

    public static void caracteresFichero(String archivo){
        try{
            FileReader reader = new FileReader(archivo); //Iniciamos el reader para leer el archvio y le pasamos como parametro el nombre del archivo
            int caracteres=0;
            while(reader.read() != -1){ //Leemos hasta que no haya más caracteres
                caracteres++;
            }
            System.out.println("------------------------------------------------------------");
            System.out.println("El archivo: " + archivo + " tiene caracteres: " + caracteres);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }
}