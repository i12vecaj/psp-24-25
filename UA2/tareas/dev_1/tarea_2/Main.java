public class Main {
    public static void main(String[] args) {
        //objeto tipo cuenta corriente con saldo inicial
        CuentaCorriente cuenta = new CuentaCorriente(10.00);
        System.out.println("Saldo inicial: " + cuenta.getSaldo() + "€.");

        //hilos con las cantidades
        HiloIngreso hilo1 = new HiloIngreso(cuenta, 100.0, "Hilo 1");
        HiloIngreso hilo2 = new HiloIngreso(cuenta, 50.0, "Hilo 2");
        HiloIngreso hilo3 = new HiloIngreso(cuenta, 20.0, "Hilo 3");
        HiloIngreso hilo4 = new HiloIngreso(cuenta, 15.0, "Hilo 4");
        HiloIngreso hilo5 = new HiloIngreso(cuenta, 10.0, "Hilo 5");
        HiloIngreso hilo6 = new HiloIngreso(cuenta, 25.0, "Hilo 6");
        HiloIngreso hilo7 = new HiloIngreso(cuenta, 30.0, "Hilo 7");
        HiloIngreso hilo8 = new HiloIngreso(cuenta, 40.0, "Hilo 8");
        HiloIngreso hilo9 = new HiloIngreso(cuenta, 55.0, "Hilo 9");
        HiloIngreso hilo10 = new HiloIngreso(cuenta, 5.0, "Hilo 10");


        //lanza los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();

        /*mis resultados no varian. creo que el problema reside en que la JVM no me lo permite. he iniciado
        10 hilos para ver si variaban, pero tampoco; al principio habia sincronizado el metodo agregarSaldo();
        a la vez que añadia el join() statement despues de arrancar los hilos, cuando lo he quitado ha
        causado que saldoFinal se imprima antes, pero las operaciones siguen siendo correctas.
         */
    }
}
