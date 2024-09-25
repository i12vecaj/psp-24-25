package ua2tarea3fr1_javiergarcia;

import java.io.FileReader;
import java.io.File;

public class Ua2tarea3fr1_JavierGarcia {

    
    public static void main(String[] args) {
        
        //Creamos las variables que haran que los ficheros del argumento funcionen
        String nomFichero;
        int contCaracteres = 0;
        FileReader fichero = null;
        File f;
        
        //Variables para comprobar el tiempo que tarda el programa en ejecutar
        long tiempo_co, tiempo_fin;
        tiempo_co = System.currentTimeMillis();

        //bucle para repetir el proceso por cada argumento 
        for (int i = 0; i < args.length; i++) {

            
            nomFichero = args[i];
            f = new File(nomFichero);

            
            if(compruebaPath(f)){
                try {
                    fichero = new FileReader(nomFichero);
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

                //mostramos el resultado que el programa tiene que dar
                System.out.println("-----------------");
                System.out.println("FICHERO " +nomFichero+ ": CONTANDO CARACTERES ...");
                System.out.println();
                System.out.println("Número de caracteres en el archivo "+nomFichero+": "+contCaracteres);
                System.out.println("-----------------");
                System.out.println();
                System.out.println();

                //inicializamos el conntador de caracteres
                contCaracteres = 0;

            }
            else{
                System.out.println("***************");
                System.out.println("EL FICHERO " + nomFichero + " NO EXISTE EN LA RUTA ESPECIFICADA");
                System.out.println("***************");
            }
        }
        //Calculamos el tiempo y lo mostramos
        tiempo_fin = System.currentTimeMillis();
        long tiempo_total = tiempo_fin - tiempo_co;

        System.out.println();
        System.out.println("TIEMPO TARDADO EN QUE EL PROGRAMA SE EJECUTE: " + tiempo_total + " milisegundos");
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
    
}
