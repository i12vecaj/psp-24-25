package EjemplosClase;

public class Hilo2 extends Thread {
    @Override
    public void run() {
        for (char i='a'; i<='z';i++){
            System.out.println(i);
        }
    }
}
