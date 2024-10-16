public class Hilo2 implements Runnable {

    @Override
    public void run() {
        for (char variable = 'a'; variable <= 'z'; variable++)
        {
            System.out.println("Letra:" + variable);
        }
    }
}
