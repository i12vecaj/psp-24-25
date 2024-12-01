package com.ignaciodeloyoladiazjimenez.Mejoras;

public class HiloProveedor extends Thread {
  private String nombre;
  private double saldo;
  private CuentaCorriente cuenta;

  public HiloProveedor(String nombre, double saldo, CuentaCorriente cuenta) {
    setName(nombre);
    this.saldo = saldo;
    this.cuenta = cuenta;
  }

  @Override
  public void run() {
    try {
      cuenta.agregarSaldo(this.saldo); // Agregamos el saldo de este hilo a la cuenta
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
