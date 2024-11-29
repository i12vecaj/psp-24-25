import java.util.Scanner;

public class Hilo extends Thread {
    public void run(){
        System.out.println("comienza hilo");
        String resultado;
        resultado=fcadena();
        System.out.println("finaliza hilo");
    }

    public String fcadena(){
        Scanner sc = new Scanner(System.in);
        String cadena="";  // tengo que iniciar la varible porque sino me da error
        System.out.println("Ingrese un texto (para terminar introduce *):");

        boolean validador = false;
        do {
            String linea = sc.nextLine();  //recojo lo que el usuario introduce
            if (linea.equals("*")) {
                validador = true;
            } else {
                cadena += linea + "\n";  // lo concadeno a la cadena
            }
        } while (!validador);

        System.out.println("Contenido de la cadena");
        System.out.println(cadena);

        sc.close();
        return cadena;
    }
}
