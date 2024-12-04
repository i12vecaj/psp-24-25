package tarea_2.mejoras;

public class ua2tarea2fr3 {
  public static void main(String[] args) throws InterruptedException {
    // Instancio la cuenta corriente y le pongo un saldo inicial de 500
    CuentaCorriente cuentaCorriente = new CuentaCorriente();
    cuentaCorriente.setSaldo(500);
    System.out.println("La cuenta corriente comienza con: " +cuentaCorriente.getSaldo());

    //Instancio los hilos con nombres y dinero
    Thread hilo1 = new Thread(new Hilo(cuentaCorriente, 89), "Primer hilo");
    Thread hilo2 = new Thread(new Hilo(cuentaCorriente, 1), "Segundo hilo");

    hilo1.start();
    hilo2.start();

    hilo1.join();
    hilo2.join();

    /**
     * En caso de que quitemos los syncronized sale el siguiente output:
     *
     * La cuenta corriente comienza con: 500
     * Operacion generada por: Segundo hilo
     * Operacion generada por: Primer hilo
     * Saldo anterior: 500
     * Saldo anterior: 500
     * nuevo saldo: 501
     * nuevo saldo: 590
     *
     * En caso de que lo dejemos podemos ver que se completa primero uno y despues otro:
     * Operacion generada por: Primer hilo
     * Saldo anterior: 500
     * nuevo saldo: 589
     * Operacion generada por: Segundo hilo
     * Saldo anterior: 589
     * nuevo saldo: 590
      */

  }
}
