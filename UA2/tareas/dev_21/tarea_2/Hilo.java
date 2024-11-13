public class Hilo extends Thread{
    CuentaCorriete cuenta;
    float aumentar;
    String usuario;

    //Constructor de la clase Hilo
    Hilo(CuentaCorriete cuenta, float aumentar, String usuario){
        this.cuenta = cuenta;
        this.aumentar = aumentar;
        this.usuario = usuario;
    }

    //Metodo run que lanza el metodos aumentarSaldo de la calse CuentaCorriente
    @Override
    public void run() {
        try {
            cuenta.aumetarSaldo(aumentar, usuario);
        } catch (InterruptedException e) {

        }
    }
}
