package tarea_2;

import java.util.Scanner;

public class AddSaldoCuenta extends Thread{
    //atributos de la clase AddSaldoCuenta
    private float nuevo_saldo;
    private String persona;

    public AddSaldoCuenta(String persona, float nuevo_saldo){
        this.nuevo_saldo=nuevo_saldo;
        this.persona=persona;

    }
    @Override
    public void run() {

        try {
            //llamo a la clase cuenta corriente y le paso el saldo nuevo y la persona que lo ha añadido

            CuentaCorriente.addSaldo(nuevo_saldo,persona);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
