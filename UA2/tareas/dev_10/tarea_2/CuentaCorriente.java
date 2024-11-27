package com.ignaciodeloyoladiazjimenez;

import java.util.Random;

public class CuentaCorriente{
  private double saldo;
  public Random numAleatorio = new Random();

  CuentaCorriente(double saldo){
    this.saldo = saldo;
  }

  CuentaCorriente(){
    this.saldo = 100; //constructor para inicializar el saldo en 100
  }

  public int esperandoAleatorio() throws InterruptedException{ //Creditos a Juan Deogracias Dev_9
    int aleatorio = 250 + numAleatorio.nextInt(1751);
    try {
      Thread.sleep(aleatorio);
    }catch (Exception error){
      System.out.println("Error" + error);
    }
    System.out.println("El hilo ha esperado: " + aleatorio + " Milisegundos");
    return aleatorio;
  }
  public synchronized void agregarSaldo(double saldo) throws InterruptedException {
    esperandoAleatorio();
    System.out.println("El saldo anterior: " + this.saldo);
    this.saldo += saldo;
    System.out.println("El saldo nuevo: " + this.saldo);
    System.out.println(Thread.currentThread().getName());
  }

  public synchronized double getSaldo() throws InterruptedException{
    esperandoAleatorio();
    return this.saldo;
  }

  public synchronized void setSaldo(double saldo) throws InterruptedException{
    esperandoAleatorio();
    System.out.println("El saldo anterior: " + this.saldo);
    this.saldo = saldo;
    System.out.println("El saldo nuevo: " + this.saldo);
    System.out.println(Thread.currentThread().getName());
  }
  public synchronized void restarSaldo(double saldo) throws InterruptedException{
    esperandoAleatorio();
    System.out.println("El saldo anterior: " + this.saldo);
    this.saldo -= saldo;
    System.out.println("El saldo nuevo: " + this.saldo);
    System.out.println(Thread.currentThread().getName());
  }
}