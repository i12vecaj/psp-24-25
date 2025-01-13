import java.net.URL;
import java.util.Scanner;

public class socket{
  public static void main(String[] args) {

    

    String direccion;
    do{
      System.out.println("Proporcioname una URL para que te de informacion sobre ella");
      Scanner scanner = new Scanner(System.in);

      direccion = scanner.nextLine();
  
      try{
  
        URL url = new URL(direccion);
  
        System.out.println("EL nombre del host es: "+url.getHost());
        System.out.println("Su puerto es: "+ url.getPort());
        System.err.println("El tipo de consulta es:"+ url.getQuery());
  
  
      }catch(Exception e){
        
      }
    }while(!direccion.equals("localhost"));

  }
}
