import java.util.List;

public class Hilos implements Runnable {
    private List<String> listaArgumentos;
    private int codigoSalida;

    public Hilos(List<String> listaArgumentos) {
        this.listaArgumentos = listaArgumentos;
    }

    @Override
    public void run() {
        int tamaño = listaArgumentos.size();
        if (tamaño < 1) {
            codigoSalida = 1;
        } else {
            String argumento = listaArgumentos.get(0);
            try {
                int numero = Integer.parseInt(argumento);
                if (numero < 0) {
                    codigoSalida = 3;
                } else {
                    codigoSalida = 0;
                }
            } catch (NumberFormatException e) {
                codigoSalida = 2;
            }
        }
    }

    public int getCodigoSalida() {
        return codigoSalida;
    }
}
