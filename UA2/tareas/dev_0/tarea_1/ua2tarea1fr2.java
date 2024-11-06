public class ua2tarea1fr2 {
    public static void main(String[] args) {
        // La principal diferencia entre este ejercicio y el anterior es el hecho de mantener un control sobre los hilos
        // para que la variable se incremente de forma organizada
        Thread hiloOrigin= new Thread(new ua2tarea1fr2runnable());
        hiloOrigin.start();
    }
}