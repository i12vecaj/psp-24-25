public class ua2tarea1fr1 {
    public static void main(String[] args) {
        Thread hiloOrigin= new Thread(new ua2tarea1fr2runnable());
        hiloOrigin.start();
    }
}