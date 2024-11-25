package tarea_3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ua2tarea3fr1 {
  public static void main(String[] args) throws InterruptedException {
    List<String> listaArchivos = new ArrayList<>();
    listaArchivos.add("buenas.txt");
    listaArchivos.add("hola.txt");
    listaArchivos.add("adios.txt");

    List<Thread> listaDeHilos = new ArrayList<>();

    for(String elemento : listaArchivos){
      Thread nuevoHilo = new Thread(new LectorDeChars(elemento));
      nuevoHilo.start();
      listaDeHilos.add(nuevoHilo);
    }

    for(Thread hilo : listaDeHilos){
      hilo.join();
    }
    /**
     * En el caso de implementar un hilo, la ejecución es más rápida ya que lo hacen los 3 "a la vez"
     * mientras que sino es más lento
     */
  }
  public static class LectorDeChars implements Runnable{
    String documento;
    int contador;
    LectorDeChars(String documento){
      this.documento = documento;
    }

    @Override
    public void run() {
      FileReader lector = null;
      try {
        // Abro el archivo
        lector = new FileReader(documento);
        // Leo el primer caracter para asegurarme de que no esté vacio
        int caracteres = lector.read();
        // Recorre el bucle hasta que encuentre el último caracter que tiene la posición -1
        while (caracteres != -1){
          AumentarContador();
          // leo el siguiente caracter
          caracteres = lector.read();
        }
        System.out.println(documento + " tiene " + contador + " caracteres");
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }finally {
        // Este código se ejecuta siempre, da igual que entre en el catch
        // En caso de que exista un lector lo cerrará
        if(lector != null){
          try {
            lector.close();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
    void AumentarContador(){
      this.contador ++;
    }
  }
}
