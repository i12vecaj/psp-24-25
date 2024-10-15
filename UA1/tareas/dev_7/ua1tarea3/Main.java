public class Main {
    public static void main(String[] args) {
        Hilo hilo = new Hilo(args); //Le pasamos al hilo los argumentos que recibe el Main
        Thread t = new Thread(hilo);
        try {
            t.start(); //Iniciamos el hilo
            t.join();

        } catch ( Exception e) {
            System.out.println("Algo ha salido mal al ejecutar el hilo");
        }
    }
}