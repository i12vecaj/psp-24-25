public class Main {
    public static void main(String[] args) {
    Lector lector = new Lector(args);
    Thread hilo = new Thread(lector);
    hilo.start();//Iniciamos el hilo

    try {
        hilo.join();
    } catch (InterruptedException e) {
        System.out.println("El hilo se ha inerrumpido.");
    }
    }
}