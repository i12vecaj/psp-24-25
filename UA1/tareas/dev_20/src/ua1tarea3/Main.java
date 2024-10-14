package ua1tarea3;

public class Main {

  public static void main(String[] args) {

    // Instancio las clases
    Hilo hiloInstancia = new Hilo();
    hiloInstancia.args = args;
    Thread hilo = new Thread(hiloInstancia);

    try{
      // Inicializo el hilo y espero a que termine
      hilo.start();
      hilo.join();
      int exit = hiloInstancia.exitCode;
      System.exit(exit);
    }catch (Exception e){
      // En caso de que el hilo se interrumpa entrará aqui
      System.out.println("Hubo un problema: "+e);
    }

  }
}
