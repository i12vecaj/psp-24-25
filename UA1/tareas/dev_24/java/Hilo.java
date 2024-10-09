package lecturacaracteres;

import java.util.Scanner;

/**
 * @author sergi
 */
public class Hilo extends Thread {
    @Override
    public void run() {
        Scanner scn = new Scanner(System.in);
        
        System.out.print("Introduce texto: ");
        String texto = scn.nextLine();
        
        int index = texto.indexOf('*');
        System.out.println(texto.substring(0, index));
    }
}
