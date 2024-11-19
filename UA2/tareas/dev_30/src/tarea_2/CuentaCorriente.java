package tarea_2;

public class CuentaCorriente {



    private static float saldo;
    public CuentaCorriente(float saldo){

        this.saldo = saldo;

    }

    public static float getSaldo() throws InterruptedException {

        return saldo;

    }


    public static void setSaldo(float saldo) throws InterruptedException {

        CuentaCorriente.saldo = saldo;
        dormir();
    }
    //metodos sincronizado que añadirá a saldo
    /*
    * En el caso de no estar sincronizado el programa mostrará de manera desordenada el estado de todos del saldo:
    * El estado previo del saldo era de 3100.0 y el nuevo es de 3800.0 y el ingreso ha sido realizado por Manolo Torres
    * El estado previo del saldo era de 2500.0 y el nuevo es de 3100.0 y el ingreso ha sido realizado por Jose Pablo
    * El estado previo del saldo era de 2000.0 y el nuevo es de 2500.0 y el ingreso ha sido realizado por pepe Moriles
    * */

    /*
    * En el caso de tener el metodo sincronizado el programa nos mostrará de manera ordenada todos lo saldos:
    * El estado previo del saldo era de 2000.0 y el nuevo es de 2500.0 y el ingreso ha sido realizado por pepe Moriles
    * El estado previo del saldo era de 2500.0 y el nuevo es de 3200.0 y el ingreso ha sido realizado por Manolo Torres
    * El estado previo del saldo era de 3200.0 y el nuevo es de 3800.0 y el ingreso ha sido realizado por Jose Pablo
    * */


    public static synchronized  void addSaldo(float nuevoSaldo, String nombre) throws InterruptedException {

        //sumo el saldo antiguo con el saldo nuevo

        float resultado = nuevoSaldo+getSaldo();
        float previo = resultado-nuevoSaldo;


        setSaldo(resultado);

        System.out.println("El estado previo del saldo era de "+previo+" y el nuevo es de "+resultado+" y el ingreso ha sido realizado por " +nombre);



    }

    //Metodo que dormirá a los demas metodos
    public static void dormir() throws InterruptedException {
        Thread.sleep(1000);

    }

}

