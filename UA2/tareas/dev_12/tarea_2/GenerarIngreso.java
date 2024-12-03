package tarea_2;

public class GenerarIngreso extends Thread {
    private CuentaCorriente cuenta;
    private double cantidad;
    private String nombrePersona;

    public GenerarIngreso(CuentaCorriente cuenta, double cantidad, String nombrePersona) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.nombrePersona = nombrePersona;
    }

    //Llamamos al metodo ingresar de la cuenta corriente
    //Parametros: cantidad a ingresar y nombre de la persona que realiza el ingreso
    @Override
    public void run() {
        cuenta.ingresar(cantidad, nombrePersona);
    }
}

