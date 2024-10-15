public class Hilo implements Runnable {

    String [] args;

    public Hilo(String [] args){
        this.args = args; //Recibimos los argumentos y los guardamos en el atributo args
    }

    @Override
    public void run(){
        if (args.length < 1) { //Comprobamos si nos ha pasado algún argumento
            System.out.println("No se ha pasado ningún argumento.");
            System.exit(1);
        }
        try { // Con try intentamos pasar el argumento a entero, en el case en el que este argumento no sea entero se ejecuta el código del catch
            int num = Integer.parseInt(args[0]); //Transformamos el primer argumento del array a entero

            if (num < 0) { //Comprobamos si el argumento es mayor a 0
                System.out.println("El argumento es menor que 0.");
                System.exit(3);
            }
            //Si ejecuta este fragmento es porque el número es mayor o igual a 0
            System.out.println("El argumento es un número mayor o igual a 0.");
            System.exit(0);

        } catch (NumberFormatException e) {
            System.out.println("El argumento no es un número.");
            System.exit(2);
        }
    }


}
