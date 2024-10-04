public class Main {
    public static void main(String[] args) {
       
        // Crear hilos que leen e imprimen
        h1 h1 = new h1();
        h2 h2 = new h2();
        
        // Iniciar los hilos, pasamos el primer hilo como argumento del segundo
        
        h2.run(h1.run());
        
    }
}