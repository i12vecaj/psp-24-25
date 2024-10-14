import java.sql.SQLOutput;
public class Lector implements Runnable {
    private String [] args; //Declaro el vector donde se almacenaran los argumentos

    public Lector(String[] args) { //Contructor de la clase.
        this.args = args;
    }

    public void run(){
    if(args.length < 1){ //Si la longitud del vector es menor 1.
        System.out.println("No hay argumentos el valor es: ");
        System.exit(1);
    }
    for (String arg : args){ //Recorremos el vector para poder leer todos los argumentos.
        try{
            int numero = Integer.parseInt(arg);//Mediante esta funcion convierto el argumento a numero.
            if (numero < 0){ //Si el argumetno es menor que cero entra en el if
                System.out.println("El argumento es un numero menor que cero, el valor es: ");
                System.exit(3);
            }
        }catch(Exception e){ //Creamos una excepcion por si el argumento es una cadena.
            System.out.println("El argumento es una cadena, el valor es: ");
            System.exit(2);
        }
    }
        //Si no es ninguno de los anteriores sera este ultimo.
        System.out.println("Es otro tipo de argumento, el valor es: ");
        System.exit(0);
    }
}
