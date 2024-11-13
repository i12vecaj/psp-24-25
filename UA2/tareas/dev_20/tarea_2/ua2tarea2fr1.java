package tarea_2;

import java.util.Random;

public class ua2tarea2fr1 {
  public static void main(String[] args) throws InterruptedException {
    CuentaCorriente cuentaCorriente = new CuentaCorriente();
    System.out.println(cuentaCorriente.getSaldo());
    cuentaCorriente.setSaldo(200);
    System.out.println(cuentaCorriente.getSaldo());
    cuentaCorriente.agregarSaldo(99);
  }

}
// Clase que maneja el valor de la cuenta corriente
class CuentaCorriente {
  // Dejo el atributo saldo privado ya que dispongo de geters y setters (aumenta la seguridad)
  private int saldo;
  static Random random = new Random();
  CuentaCorriente(){
    this.saldo = 100;
  }

  // geter y seter con implementación de sleep
  public synchronized int getSaldo() throws InterruptedException {
    Thread.sleep(random.nextInt(250, 2000));
    return this.saldo;
  }
  public synchronized void setSaldo(int nuevoValor) throws InterruptedException {
    Thread.sleep(random.nextInt(250, 2000));
    this.saldo = nuevoValor;
  }
  // Funcion para añadir saldo a la cuenta corriente
  public synchronized void agregarSaldo(int addicion){
    System.out.println("Operacion generada por: " + Thread.currentThread().getName());
    System.out.println("Saldo anterior: " + this.saldo);
    this.saldo += addicion;
    System.out.println("nuevo saldo: " + this.saldo);
  }

}

