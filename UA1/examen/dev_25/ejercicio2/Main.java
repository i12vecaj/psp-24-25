public class Main {
    public static void main(String[] args) {

        //Creamos tres objetos de la clase Runnable con los valores de los hilos.
        Thread Temperatura = new Thread(new Temperatura());
        Thread Humedad = new Thread(new Humedad());
        Thread Estado = new Thread(new Estado());

        //Iniciamos los hilos.
        Temperatura.start();
        Humedad.start();
        Estado.start();
    }
}