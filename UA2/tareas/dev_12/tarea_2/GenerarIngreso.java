package tarea2;

public class GenerarIngreso extends Thread {
    private CuentaCorriente cuenta;
    private double cantidad;
    private String nombrePersona;

    public GenerarIngreso(CuentaCorriente cuenta, double cantidad, String nombrePersona) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.nombrePersona = nombrePersona;
    }

    @Override
    public void run() {
        cuenta.ingresar(cantidad, nombrePersona);
    }
}

