public class Iniciador {
    public static void main(String[] args) {
        new Thread(new NodoToken(1, 10000, true, false)).start();
        new Thread(new NodoToken(2, 10001, false, false)).start();
        new Thread(new NodoToken(3, 10002, false, true)).start();
    }
}


