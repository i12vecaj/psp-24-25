package tarea_2.mejoras;

public class CuentaCorrienteMejoras {
    private static float saldo;

    public CuentaCorrienteMejoras(float saldo) {
        this.saldo = saldo;
    }

    public static float getSaldo() throws InterruptedException {
        return saldo;
    }

    public static synchronized void setSaldo(float saldo) throws InterruptedException {
        CuentaCorrienteMejoras.saldo = saldo;
        dormir();
    }

    // Método sincronizado que añadirá saldo
    public static synchronized void addSaldo(float nuevoSaldo, String nombre) throws InterruptedException {
        float resultado = nuevoSaldo + getSaldo();
        float previo = resultado - nuevoSaldo;

        setSaldo(resultado);

        // Imprime el estado del saldo antes y después de la adición
        System.out.println("El estado previo del saldo era de " + previo + " y el nuevo es de " + resultado + " y el ingreso ha sido realizado por " + nombre);
    }

    // Método que duerme el hilo para simular un retraso
    public static void dormir() throws InterruptedException {
        Thread.sleep(1000);
    }
}
