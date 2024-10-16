public class Main {
    public static void main(String[] args) {
        //comprobamos que si no entran argumentos se termina el programa
        if(args.length <1){
            System.exit(1);
        }
        // en este bloque try catch comprobamos que el argumento sea un entero
        // si no es un numero termina el programa
        // si lo es, comprobamos que sea mayor que 0
        try{
            int num = Integer.parseInt(args[0]);
            if(num < 0){
                System.exit(3);
            }
        }catch (NumberFormatException e){
            System.exit(2);
        }
        // si todo es correcto se termina el programa
        System.exit(0);
    }
}
