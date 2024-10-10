public class Hilo1 extends Thread {
    private String[] args;
    private int codigoSalida;

    public Hilo1(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        if (args.length < 1) {
            codigoSalida = 1; // Si no pasa ningun argumento
            return; // Salimos del hilo
        }

        if (!isInteger(args[0])) {
            codigoSalida = 2; // Es una cadena de texto
            return; // Salimos del hilo
        }

        int numero = Integer.parseInt(args[0]);

        if (numero < 0) {
            codigoSalida = 3; // Si es un numero negativo
            return; // Salimos del hilo
        }

        codigoSalida = 0; // Cualquier otro caso
    }

    // Verificamos si es un entero
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // Si es un entero
        } catch (NumberFormatException e) {
            return false; // No es un entero
        }
    }

    public int getCodigoSalida() {
        return codigoSalida;
    }
}
