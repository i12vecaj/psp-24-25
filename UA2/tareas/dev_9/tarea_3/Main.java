package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ficheros> hilos = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);


        String nombre_archivo;
        do {
            System.out.println("Introduce el nombre del fichero (introduce '*' para terminar):");
            nombre_archivo = scanner.nextLine();
            if (!nombre_archivo.equals("*")) {
                list.add(nombre_archivo);
            }
        } while (!nombre_archivo.equals("*"));


        /*
         * FORMA SECUENCIAL (HILOS)
         * */

        System.out.println("\n--- Ejecución Secuencial ---");
        long startSequential = System.currentTimeMillis();
        for (String name : list) {
            countCharactersSequential("src/main/java/org/example/" + name + ".txt");
        }
        long endSequential = System.currentTimeMillis();
        System.out.println("Tiempo de la primera parte: " + (endSequential - startSequential) + " milisegundos");
        /*
        * FORMA CONCURRENTE (HILOS)
        * */

        long startConcurrent = System.currentTimeMillis();

        //Esto es una forma de manejar listas en java
        list.stream()
                .map(nombre -> new Ficheros("src/main/java/org/example/" + nombre))
                .peek(hilo -> hilo.start()) //peek permite realizar una tarea intermedia y luego con foreach lo añadimos
                .forEach(hilos::add);

        //Esperamos a que los hilos terminen
        for (Ficheros hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error esperando al hilo: " + e.getMessage());
            }
        }
        long endConcurrent = System.currentTimeMillis();
        System.out.println("Tiempo en la segunda parte: " + (endConcurrent - startConcurrent) + " milisegundos ");

        System.out.println("Todos los hilos han terminado.");
    }
    // Método para contar caracteres de forma secuencial
    public static void countCharactersSequential(String fileName) {
        try (FileReader fr = new FileReader(fileName)) {
            int charCount = 0;
            int c;
            while ((c = fr.read()) != -1) {
                charCount++;
            }
            System.out.println("Archivo de texto: " + fileName + " - Caracteres: " + charCount);
        } catch (IOException e) {
            System.err.println("Error procesando archivo " + fileName + ": " + e.getMessage());
        }
    }
}