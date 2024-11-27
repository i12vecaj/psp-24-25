package com.ignaciodeloyoladiazjimenez;

public class HiloProveedor extends Thread{

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
      cuenta.agregarSaldo(this.saldo); //Agregamos el saldo de este hilo a el de la clase CuentaCorriente con el metodo agregarSaldo de dicha clase.
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}


