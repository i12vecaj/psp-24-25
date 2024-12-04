package tarea_3.mejoras;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ua2tarea3fr1 {

  public static void main(String[] args) throws InterruptedException {
    // Lista de archivos a leer
    String[] listaArchivos = {"buenas.txt", "hola.txt", "adios.txt"};

    // Lista de hilos
    Thread[] listaDeHilos = new Thread[listaArchivos.length];

    for (int i = 0; i < listaArchivos.length; i++) {
      // Iniciamos un hilo para leer cada archivo
      String archivo = listaArchivos[i];
      listaDeHilos[i] = new Thread(new LectorDeChars(archivo));
      listaDeHilos[i].start();
    }

    // Esperamos a que todos los hilos terminen
    for (Thread hilo : listaDeHilos) {
      hilo.join();
    }
  }

  public static class LectorDeChars implements Runnable {

    private final String documento;
    private final AtomicInteger contador;

    // Constructor de la clase LectorDeChars
    public LectorDeChars(String documento) {
      this.documento = documento;
      this.contador = new AtomicInteger(0);  // Usamos AtomicInteger para manejo seguro de hilos
    }

    @Override
    public void run() {
      FileReader lector = null;
      try {
        // Intentamos abrir el archivo para lectura
        lector = new FileReader(documento);
        // Leemos el primer caracter para asegurarnos de que el archivo no está vacío
        int caracteres = lector.read();

        // Recorremos el archivo hasta el final
        while (caracteres != -1) {
          aumentarContador();
          // Leemos el siguiente caracter
          caracteres = lector.read();
        }
        // Mostramos el resultado en consola
        System.out.println(documento + " tiene " + contador.get() + " caracteres");
      } catch (FileNotFoundException e) {
        System.err.println("El archivo " + documento + " no fue encontrado.");
      } catch (IOException e) {
        System.err.println("Error al leer el archivo " + documento + ": " + e.getMessage());
      } finally {
        // Cerramos el lector de archivos
        if (lector != null) {
          try {
            lector.close();
          } catch (IOException e) {
            System.err.println("Error al cerrar el archivo " + documento);
          }
        }
      }
    }

    // Método sincronizado para aumentar el contador
    private synchronized void aumentarContador() {
      contador.incrementAndGet(); // Usamos AtomicInteger para incrementar de manera segura en hilos
    }
  }
}