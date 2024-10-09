public class Main {
    public static void main(String[] args) {
        // Crear hilos que leen e imprimen
        Hilo1 h1 = new Hilo1();
        Hilo2 h2 = new Hilo2();

        // Iniciar los hilos, pasamos el primer hilo como argumento del segundo

        h2.run(h1.run());
    }
}