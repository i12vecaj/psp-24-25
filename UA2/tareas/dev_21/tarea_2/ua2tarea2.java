public class Main {
    public static void main(String[] args) throws InterruptedException {

        //Instanciamos la clase cuenta y los hilos
        CuentaCorriete cuenta = new CuentaCorriete(1000);
        Hilo pepe = new Hilo(cuenta, 50, "Pepe");
        Hilo javi = new Hilo(cuenta, 350, "Javi");
        Hilo oscar = new Hilo(cuenta, 100, "Óscar");

        System.out.println("El saldo actual de la cuenta es de "+ cuenta.getSaldo());

        //Lanzamos los hilos
        pepe.start();
        javi.start();
        oscar.start();

        //Y esperamos que termine la ejecución
        try{
            pepe.join();
            javi.join();
            oscar.join();
        }catch (Exception e){

        }

        System.out.println("El saldo final de la cuenta es de: "+ cuenta.getSaldo());
    }

    /*
    * Resultado con synchronized:
    *
    * El saldo actual de la cuenta es de 1000.0
    * Javi esta intentado ingresar 350.0$. El salario actual de la cuenta es: 1000.0
    * Ingreso realiazado por Javi, el salario actual es: 1350.0
    * Pepe esta intentado ingresar 50.0$. El salario actual de la cuenta es: 1350.0
    * Óscar esta intentado ingresar 100.0$. El salario actual de la cuenta es: 1400.0
    * Ingreso realiazado por Pepe, el salario actual es: 1500.0
    * Ingreso realiazado por Óscar, el salario actual es: 1500.0
    * El saldo final de la cuenta es de: 1500.0
    *
    * Resultado sin synchronized:(El mismo en cuanto al saldo de la cuenta)
    *
    * El saldo actual de la cuenta es de 1000.0
    * Javi esta intentado ingresar 350.0$. El salario actual de la cuenta es: 1000.0
    * Óscar esta intentado ingresar 100.0$. El salario actual de la cuenta es: 1350.0
    * Ingreso realiazado por Óscar, el salario actual es: 1450.0
    * Pepe esta intentado ingresar 50.0$. El salario actual de la cuenta es: 1450.0
    * Ingreso realiazado por Javi, el salario actual es: 1500.0
    * Ingreso realiazado por Pepe, el salario actual es: 1500.0
    * El saldo final de la cuenta es de: 1500.0
    *
    * Conclusión:
    *
    * El resultado es el mismo porque el codigo se esta ejecutando de formas secuencial y creo que durante el sleep del hilo que acabamos de
    * no le da tiempo a ejecutar el siguiente hilo. Tambien creo que puede ser debido a la configuración de la maquina en el que lo estoy
    * ejecutando.
    *
    * */
}