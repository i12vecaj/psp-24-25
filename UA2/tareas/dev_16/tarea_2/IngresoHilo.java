public class IngresoHilo extends Thread {
    private CuentaCorriente cuenta;
    private double cantidad;

    public IngresoHilo(CuentaCorriente cuenta, double cantidad, String nombre) {
        super(nombre);
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        cuenta.ingresar(cantidad, getName());
    }
}