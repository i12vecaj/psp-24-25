
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alvar
 */
public class Lanzar {
    
      public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        String letra = "";
        String lista = "";
        
       while(!letra.equals("*")){
          
          lista += letra; 
          letra = entrada.next();
          
   
       }
        System.out.println("lista "+lista);
       
       
    }
    
    
}
