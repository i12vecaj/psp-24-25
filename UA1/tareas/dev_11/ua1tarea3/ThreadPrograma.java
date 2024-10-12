//Implemento un hilo
public class ThreadPrograma implements Runnable {
    private String[] args;
    private String mensaje;
    public int exitCode;

    //Declaro el constructor de la clase
    public ThreadPrograma(String[] args) {
        this.args = args;
        this.mensaje = "";
        this.exitCode = -1;
    }

    @Override
    public void run() {

        //Si no hay argumentos, imprimo el mensaje de que no hay argumentos y el exitCode pasa a ser 1 y salgo del programa
        if (args.length < 1) {
            mensaje = "Esto indica que no hay argumentos.";
            exitCode = 1;
            System.out.println(mensaje);
            return;
        }
        //Bucle para recorrer todos los argumentos
        for (String arg : args) {
            try {
                int numero = Integer.parseInt(arg); //Casteamos los argumentos para pasarlo a entero y comprobar si son numeros negativos o no

                //Si es un número negativo, imprimo el mensaje de que se trata de un numero menor a 0 y el exitCode pasa a ser 3 y salgo del programa
                if (numero < 0) {
                    mensaje = "Esto indica que el argumento es un número menor a 0.";
                    exitCode = 3;
                    System.out.println(mensaje);
                    return;
                }

            } catch (NumberFormatException e) {
                //Si no es un número, imprimo el mensaje de que es una cadena y el exitCode pasa a ser 2 y salgo del programa
                mensaje = "Esto indica que el argumento es una cadena.";
                exitCode = 2;
                System.out.println(mensaje);
                return;
            }
        }

        System.out.println("Otro caso, el programa devuelve 0.");
        System.exit(0);


    }

    //Metodo para devolver los datos de la clase para usarlos en los tests
    public Object[] getDatos() {
        return new Object[] {mensaje, exitCode};
    }

}
