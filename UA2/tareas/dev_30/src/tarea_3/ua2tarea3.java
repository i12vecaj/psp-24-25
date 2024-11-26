package tarea_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ua2tarea3 {
    public static void main(String[] args) {
        //lista para almacenar el nombre de los txt
        ArrayList<String> nombresArchivos = new ArrayList<String>();
        //nombresArchivos.add("ManuelTorres.txt");
        Scanner scanner = new Scanner(System.in);
        String nombreArchivo;
        //permitimos que el usuario introduzca el nombre de mas de un archivo hasta que introduzca el caracter *
        do {
            System.out.println("Introduzca el nombre del fichero del archivo, para terminar introduzca (*):");
            nombreArchivo = scanner.nextLine();

            if (!nombreArchivo.equals("*")) {
                nombresArchivos.add(nombreArchivo);
            }
        }while (!nombreArchivo.equals("*"));
        scanner.close();

        //creamos una lista para todos los hilos

        List<Thread> hilos = new ArrayList<>();
        //recorremos la lista de los nombre de los txt
        for (String archivo : nombresArchivos) {
            Consumidor hilo = new Consumidor(archivo + ".txt");
            hilos.add(hilo);
            hilo.start();
        }

        //recorremos la lista de los hilos para ordenarlos usando join
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + hilo.getName());
            }
        }

        System.out.println("Todos los archivos han sido procesados.");
    }
}