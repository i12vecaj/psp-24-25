package com.ignaciodeloyoladiazjimenez.Mejoras;

public class HiloConsumidor extends Thread {
  private String nombre;
  private double saldo;
  private CuentaCorriente cuenta;

  public HiloConsumidor(String nombre, double saldo, CuentaCorriente cuenta) {
    setName(nombre);
    this.saldo = saldo;
    this.cuenta = cuenta;
  }

  @Override
  public void run() {
    try {
      cuenta.restarSaldo(this.saldo); // Restamos el saldo de este hilo a la cuenta
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
