// Programa que ejecuta el hilo que contiene la logica del ejercicio
public class Main {

    public static void main(String[] args) {
        // Para comenzar creamos una instancia de la clase Hilo
        Hilo hilo = new Hilo();
        Hilo2 hilo2 = new Hilo2();

        // Thread se usa para crear el hilo
        Thread crear_hilo = new Thread(hilo);
        Thread crear_hilo2 = new Thread(hilo2);

        // Iniciamos el hilo
        crear_hilo.start();
        crear_hilo2.start();

        // En el momento en el que se termine la ejecucion del hilo se mostrara este mensaje
        System.out.println("Programa finalizado.Gracias por todo");
    }
}