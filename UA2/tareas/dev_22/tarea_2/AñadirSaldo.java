public class AñadirSaldo extends Thread{

    private String nuevoUsuario;
    private float nuevoSueldo;

    public AñadirSaldo(String nuevoUsuario, float nuevoSueldo) {
        this.nuevoUsuario = nuevoUsuario;
        this.nuevoSueldo = nuevoSueldo;
    }

    @Override
    public void run() {
        try{
            CuentaCorriente.historicoCuenta(nuevoSueldo, nuevoUsuario);

        } catch (Exception e){
            System.out.println( "No se ha podido añadir el nuevo saldo");
        }





    }
}
