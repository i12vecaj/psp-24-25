package Ejercicios_Java.Tarea3;

public class HiloNumeros implements Runnable {
    private String[] args;

    public HiloNumeros( String[] args ) {
        this.args = args;

    }


    @Override
    public void run() {
        try{

            int numero = Integer.parseInt(args[0]);

            if ( numero < 0) {

                System.out.println("numero menor que 0");
                System.exit(3);

            }else {

                System.out.println("numero menor que 1");
                System.exit(1);
                
            }
        }catch (Exception e){

                System.out.println("Ha introducido una cadena de caracteres");
                System.exit(0);

        }

    }
}
