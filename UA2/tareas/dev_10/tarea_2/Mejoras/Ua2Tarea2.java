package com.ignaciodeloyoladiazjimenez.Mejoras;

public class Ua2Tarea2 {

  public static void main(String[] args) throws InterruptedException {
    CuentaCorriente cuenta = new CuentaCorriente();
    HiloConsumidor hiloConsumidor = new HiloConsumidor("Hilo Consumidor", 50, cuenta);
    HiloProveedor hiloProveedor = new HiloProveedor("Hilo Proveedor", 40, cuenta);

    System.out.println("Saldo inicial: " + cuenta.getSaldo());

    hiloConsumidor.start();
    hiloProveedor.start();
  }
}
