package tarea_2.mejoras;

import tarea_2.CuentaCorriente;

public class AddSaldoCuentaMejoras implements Runnable {
    // Atributos de la clase AddSaldoCuenta
    private float nuevoSaldo;
    private String persona;

    public AddSaldoCuentaMejoras(String persona, float nuevoSaldo) {
        this.nuevoSaldo = nuevoSaldo;
        this.persona = persona;
    }

    @Override
    public void run() {
        try {
            // Llamo a la clase CuentaCorriente y le paso el saldo nuevo y la persona que lo ha añadido
            CuentaCorriente.addSaldo(nuevoSaldo, persona);
        } catch (InterruptedException e) {
            System.err.println("Error al agregar saldo: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
        }
    }
}
