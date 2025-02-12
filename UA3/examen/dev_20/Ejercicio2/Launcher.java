package Ejercicio2;


//launcher para lanzar a todos los clientes que queramos
public class Launcher {
    public static void main(String[] args) {
        new Thread(new Cliente(8000)).start();
        new Thread(new Cliente(8001)).start();

    }
}
