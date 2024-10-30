package UA1.tareas.dev_10.ua1tarea3;
public class Main {
  public static void main(String[] args) {
    // Crear una instancia de la clase Runnable
    hilo hilo1 = new hilo(args);
    Thread thread = new Thread(hilo1);
    thread.start();

    try {
      thread.join();//Espero a que termine el hilo
    } catch (InterruptedException e) {
      System.out.println("Hilo interrumpido");
    }
    System.out.println("Fin del programa");
    if(hilo1.exit !=570){
      System.exit(hilo1.exit);
    }else{
      System.exit(0);
    }
  }
}



