public class CuentaCorriete {
    private float saldo;

    //Constructor de la cuenta
    CuentaCorriete(float salario){
        this.saldo = salario;
    }

    //Metodo get que tiene dentro un sleep de entre 0.25 y 2 segundos
    public float getSaldo() throws InterruptedException {
        Thread.sleep(tiempoRandom());
        return saldo;
    }
    //Metodo set que tiene dentro un sleep de entre 0.25 y 2 segundos
    public void setSaldo(float salario) throws InterruptedException {
        Thread.sleep(tiempoRandom());
        this.saldo = salario;
    }

    //Metodo que permite aunmentar el salario de la cuenta.
    public synchronized void aumetarSaldo(float cantidad, String usuario) throws InterruptedException {
        System.out.println(usuario+" esta intentado ingresar "+cantidad+"$. El salario actual de la cuenta es: "+getSaldo());
        saldo += cantidad;
        System.out.println("Ingreso realiazado por "+ usuario +", el salario actual es: "+getSaldo());

    }

    // Funcion que da un int aletorio entre 250 y 2000.
    public int tiempoRandom(){return (int) (Math.random() * 1751) + 250;}


}
