package EjemplosClase;

public class Main {
    public static void main(String[] args) {
        //Forma buena de hacerlo
        Thread numeros = new Thread( new Hilos1());
        //Forma mala de hacerlo
        Hilo2 hilo2 = new Hilo2();
        numeros.start();
        hilo2.start();
    }
}
