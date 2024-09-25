package ua2tarea3fr2_javiergarcia;

import java.io.FileReader;
import java.io.File;


class Contador
{
    //creamos las variables
    int contCaracteres = 0;

    private String nombre = "";  
    File f;

    //Creamos el constructor
    Contador (String nombre) {
        this.nombre = nombre;
        f = new File(nombre);
    }

    
    public static boolean compruebaPath(File f) {
        boolean result = false;

        if(f.exists()){
            if(f.isFile()){
                if(f.canRead()){
                    result = true;
                }
            }
        }

        return result;
    }

    public void incrementa() {
        //Con el FileReades contaremos los caracteres del fichero
        FileReader fichero = null;

        if(compruebaPath(f)){
            try {
            fichero = new FileReader(nombre);
            int caracter = fichero.read();
            contCaracteres = 1;
            
            while(caracter != -1) {
                caracter = fichero.read();
                contCaracteres++;
            }
            fichero.close();
            }catch(Exception e){
                
                System.out.println("Error: "+e.getMessage());
            }

            //Mostramos el total de caracteres
            System.out.println("--------------------------------------------------------");
            System.out.println("FICHERO " +nombre+ ": CONTANDO CARACTERES ...");
            System.out.println();
                System.out.println("Número de caracteres en el archivo "+nombre+": "+contCaracteres);
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println();

            //Volvemos a inicializar a 0 el contador de caracteres
            contCaracteres = 0;
        }
        //Si el fichero no existe en la ruta introducida, mostraremos el error
        else{
            System.out.println("***********************************");
            System.out.println("EL FICHERO " + nombre + " NO EXISTE EN LA RUTA ESPECIFICADA");
            System.out.println("***********************************");
            System.out.println();
            System.out.println();
        }

    }

    public int valor() {
        //inicializamos el valor del contador
        return contCaracteres;
    }
}

class HiloContador extends Thread{
//creaos la clase de los hilos

private Contador contador;

    //creamos el constructor
    public HiloContador(String nombre, Contador titulo) {
        setName(nombre);
        contador = titulo;
    }

    public void run() {
        contador.incrementa();
    }

}

public class Ua2tarea3fr2_JavierGarcia {

    
    public static void main(String[] args) {
        //Creamos las variables que haran que los ficheros del argumento funcionen
        String nomFichero;
        long tiempo_co, tiempo_fin;
        tiempo_co = System.currentTimeMillis();

        //bucle para repetir el proceso por cada argumento 
        for (int i = 0; i < args.length; i++) {


            //En cada iteración del for guardamos el valor del argumento en la posicion actual a nombreFichero
            nomFichero = args[i];
            Contador contador = new Contador(nomFichero);

            //Instanciamos un hilo que sobrescribiremos para contar los caracteres de cada fichero
            HiloContador hiloContador = new HiloContador("Hilo "+i, contador);

            //inicializamos el hilo
            hiloContador.start();

            //Implementamos el hilo en ejecución
            try {
                hiloContador.join();
            }catch (Exception e) {
                
            }
        }

        tiempo_fin = System.currentTimeMillis();
        long tiempo_total = tiempo_fin - tiempo_co;

        System.out.println();
        System.out.println("TIEMPO TARDADO EN QUE EL PROGRAMA SE EJECUTE: " + tiempo_total + " milisegundos");
    }
    
}
