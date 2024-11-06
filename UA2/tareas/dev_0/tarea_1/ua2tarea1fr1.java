public class Main {
    public static void main(String[] args) {
        Thread hilo = new Thread(new hiloPrincipal());
        hilo.start();
    }
}

class hiloPrincipal implements Runnable {
    int num = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Hilo hilo = new Hilo();
            hilo.start();
        }
    }

    class Hilo extends Thread {
        @Override
        public void run() {
            num += 1000;
            System.out.println(num);
        }
    }
}

