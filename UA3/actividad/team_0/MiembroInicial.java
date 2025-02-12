public class MiembroInicial {
    public static void main(String[] args) {
        NodoToken nodo1 = new NodoToken(1, 10000, true, false);
        new Thread(nodo1).start();
    }
}