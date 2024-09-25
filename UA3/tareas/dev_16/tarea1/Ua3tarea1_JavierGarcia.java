import java.net.*;
import java.util.Scanner;

public class Ua3tarea1_JavierGarcia {

    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sca=new Scanner(System.in);
        
        //Se crean las variable que vamos a utilizar en el código
        URL url;//Variable en la que se guarda la url
        String direccion;//Variable en la que se guarda la url que el usuario introduce
        
        //Hacemos un do while para que se repita el proceso hasta que se introduzca "localhost"
        do{
            //se le pide al usuario la url 
            System.out.println("Introduce una URL para visualizar su información");
            System.out.println("Cuando introduzcas 'localhost' terminara el programa");
            direccion=sca.nextLine();//Se guarda la informacion que el usuario mete en la variable
            
            try{
                System.out.println("Constructor simple para una URL:");
                url = new URL(""+direccion);//Le pasamos la direccion introducida a la url
                Visualizar(url);//Se llama a la funcion Viualizar() para ver la informacion de la url

            }catch(Exception e){
                System.out.println(e);
                System.out.println("--------------");
            }
        }while(!direccion.equals("localhost"));//Termina el do while
            
    }
    
    // El metodo Visualizar() muestra informacion de la URL usando los metodos de la clase URL.
    private static void Visualizar(URL url){
		System.out.println("\tURL completa: " + url.toString());
		System.out.println("\tgetProtocol(): " + url.getProtocol());
		System.out.println("\tgetHost(): " + url.getHost());
		System.out.println("\tgetPort(): " + url.getPort());
		System.out.println("\tgetFile(): " + url.getFile());
		System.out.println("\tgetUserInfo(): " + url.getUserInfo());
		System.out.println("\tgetPath(): " + url.getPath());
		System.out.println("\tgetAuthority(): " + url.getAuthority());
		System.out.println("\tgetQuery(): " + url.getQuery());
		System.out.println("\tgetDefaultPort(): "+ url.getDefaultPort());
		System.out.println("==================================================");
	}
    
    
}
