import java.util.Random;

public class CuentaCorriente implements Runnable{
    private double saldo;
    CuentaCorriente(double saldo){
        this.saldo = setSaldo(saldo);
    }
    public void run(){
        Random r = new Random();
        int num = r.nextInt(1750)+250;
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private double setSaldo(double saldo){
        this.saldo = saldo;
        run();
        return this.saldo;
    }
    public double getSaldo(){
        run();
        return this.saldo;
    }
    public void añadirSaldo(double saldo,double aSumar){
        double total = this.saldo + aSumar;
        System.out.println("El saldo es: " + saldo + " y se va a sumar: " + aSumar + " y el total es: " + total);
        this.saldo = setSaldo(total);

    }
}
