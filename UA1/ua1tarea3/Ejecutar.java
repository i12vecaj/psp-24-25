import java.sql.SQLOutput;
public class Ejecutar implements Runnable {

    //Declaro el vector donde se van a almacenar los argumentos
    private String [] args;
    //Contructor
    public Ejecutar(String[] args) {
        this.args = args;
    }

    public void run(){

        //Si la longitud del vector es menor 1.
        if(args.length < 1){
            System.out.println("No hay argumentos el valor es: 1 ");
            System.exit(1);
        }
        //Recorremos el vector para poder leer todos los argumentos.
        for (int i = 0; i < args.length; i++){
            try{

                //funcion para convertir los argumentos a numero.
                int numero = Integer.parseInt(args[i]);
                //Si el argumetno es menor que cero entra en el if
                if (numero < 0){
                    System.out.println("El argumento es un numero menor que cero, el valor es: 3");
                    System.exit(3);
                }
                //Creamos una excepcion por si el argumento es una cadena.
            }catch(Exception e){
                System.out.println("El argumento es una cadena, el valor es: 2");
                System.exit(2);
            }
        }
        //Si no es ninguno de los anteriores sera este ultimo.

        System.out.println("Es otro tipo de argumento, el valor es: 0");
        System.exit(0);
    }
}