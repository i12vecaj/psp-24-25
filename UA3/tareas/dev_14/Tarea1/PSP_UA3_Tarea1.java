/**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 *  
 *  Enunciado:
 *  
 *  Crea un programa en Java que admita desde la línea de comandos una URL y visualice información sobre esta. Modifica el programa para que admita continuamente nuevas IP o URL y muestre 
 *  la información hasta que el usuario inserte "localhost".
 * 
 *  @class   PSP_UA3_Tarea1
 * 
 */

import java.net.*; // Para poder trabajar a nivel de red y transporte
import java.util.Scanner; // Para poder introducir datos

public class PSP_UA3_Tarea1 {
    

    public static void main(String args[]) throws UnknownHostException 
    {


/**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 * 
 *  Declaramos un scan para poder introducir la url a mano
 *  
 *  @param dir,direccion
 * 
 */

        Scanner scan = new Scanner(System.in);
        InetAddress dir = null;
        String direccion;
        do
        {
            System.out.println("Introduce la direccion: ");
            direccion = scan.next();

 /**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 * 
 *  Control de errores TryCatch
 *  
 */
            try{
    
    
              
                dir =  InetAddress.getByName(direccion);
                pruebaMetodos(dir);
        
/**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 * 
 *  @param (Tipo vector) direcciones
 * 
 *  En las siguientes sentencias estamos programando la salida de las diferentes direcciones
 *  IP asignadas al nombre de dominio que hemos introducido con anterioridad.
 *  
 */             
        
                System.out.println("Direcciones IP para: "+dir.getHostName());
                InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
                for (int i=0; i<direcciones.length;i++)
                {
        
                    System.out.println("\t\t"+direcciones[i].toString());
                    System.out.println("========================================");
                }
            }catch(UnknownHostException e1){e1.printStackTrace();}
    
/**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 * 
 *  @param url
 * 
 *  En la siguiente sentencia se hace el uso de TryCatch en el que se programará la salida 
 *  de información de la URL introducida.
 *  
 */    
    
            URL url;
    
    
            try{
    
                System.out.println("Constructor simple para oracle");
                url = new URL("https://"+direccion);
                visualizar(url);
    
    
    
    
    
            }catch(MalformedURLException e){System.out.println(e);}
    

        }while(direccion=="localhost");
       
    }

/**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 * 
 *  @function: visualizar
 * 
 *  Función que se encarga de la visualización de la información de la url
 *  que hemos introducido
 *  
 */ 


private static void visualizar (URL url)
{

    System.out.println("\tURL completa: "+url.toString());
    System.out.println("\tgetProtocol(): "+url.getProtocol());
    System.out.println("\tgetHost(): "+url.getHost());
    System.out.println("\tgetPort(): "+url.getPort());
    System.out.println("\tgetFile(): "+url.getFile());
    System.out.println("\tgetUserInfo(): "+url.getUserInfo());
    System.out.println("\tgetPath(): "+url.getPath());
    System.out.println("\tgetAuthority(): "+url.getAuthority());
    System.out.println("\tgetQuery(): "+url.getQuery());
    System.out.println("\tgetDefaultPort(): "+url.getDefaultPort());
    System.out.println("\t========================================");
}  

/**
 *  PSP_UA3_Tarea1
 *  @author Daniel Fernández Balsera
 *  Curso: 2º DAM
 *  Asignatura: Programación de Servicios y Procesos
 * 
 *  @function: pruebaMetodos
 * 
 *  Funcion que se carga de obtener los datos a nivel de red 
 *  y transporte  relacionados  con  dicha dirección.
 *  
 */ 

private static void pruebaMetodos(InetAddress dir)
{

    System.out.println("\tMetodo getByname(): " + dir);
    InetAddress dir2;
    try
    {

        dir2 = InetAddress.getLocalHost();
        System.out.println("\t Metodo getLocalhost(): "+ dir2);

    }catch (UnknownHostException e){

        e.printStackTrace();
        
    }



    System.out.println("\t Metodo getHostName(): "+dir.getHostName());
    System.out.println("\t Metodo getHostAddress: "+dir.getHostAddress());
    System.out.println("\t Metodo toString(): "+dir.toString());
    System.out.println("\t Metodo getCanonicalHostname(): "+dir.getCanonicalHostName());
}

}
