public class MiembroFinal {
    public static void main(String[] args) {
        NodoToken nodo3 = new NodoToken(3, 10002, false, true);
        new Thread(nodo3).start();
    }
}