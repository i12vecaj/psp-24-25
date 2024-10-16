import java.io.IOException; //importamos la clase IOException para el manejo de excepciones
public class EjecutarPrograma {
    public static void main(String[] args){
        //en este bloque try catch creamos un vector que va a tener la longitud de arg + 2
        // al hacerlo podemos asignar una cadena de caracteres vacías a la posición 0 y 1
        // para crear artificialmente un comando de ejecucion
        try {
            String[] comando = new String[args.length+2];
            comando[0] = "java";
            comando[1] = "Main";
            //una vez creado el array de strings copiamos los argumentos
            System.arraycopy(args, 0, comando, 2, args.length);
            //creamos un proceso con el comando que acabamos de crear
            Process p = Runtime.getRuntime().exec(comando);
            //esperamos a que el proceso termine
            int exitcode = p.waitFor();
            System.out.println("El programa ha concluido con el código de salida: " + exitcode);
            //en caso de error, se imprime el por pantalla la excepcion que ha sucedido
        } catch (IOException | InterruptedException e){
            System.out.println("Error al ejecutar el programa");
        }
    }
}
