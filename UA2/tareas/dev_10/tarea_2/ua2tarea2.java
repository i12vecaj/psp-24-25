package com.ignaciodeloyoladiazjimenez;
import java.util.Random;
public class ua2tarea2 {

  public static void main(String[] args) throws InterruptedException {
    CuentaCorriente cuenta = new CuentaCorriente();
    HiloConsumidor hiloConsumidor = new HiloConsumidor("Hilo Consumidor",50,cuenta);
    HiloProveedor hiloProveedor = new HiloProveedor("Hilo Proveedor",40,cuenta);
    System.out.println(cuenta.getSaldo());
    hiloConsumidor.start();
    hiloProveedor.start();
  }
}
