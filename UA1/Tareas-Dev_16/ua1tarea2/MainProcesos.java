import java.util.Scanner;

public class MainProcesos {
    public static void main(String[] args) { //Creo un objeto Scanner para poder leer lo que el usuario escribe en el programa.
        Scanner scanner = new Scanner(System.in);

        //Uso un objeto de tipo StringBuilder que sirve para guardar todo el texto que el usuario escriba.
        StringBuilder inputText = new StringBuilder();

        //Le decimos al usuario que puede escribir lo que el quiera pero cuando ponga un asterico (*) el programa se acaba.
        System.out.println("Escribe lo que quieras, cuando desees terminar pon un asterisco (*):");

        try {
            //Meto un buce While que es un bucle infinito que lee todas lineas de texto hasta que el usuario escriba un asterisco.
            while (true) {
                //Leo la linea completa que el usuario ha escrito y se guarda en una variable llamada 'input'.
                String input = scanner.nextLine();

                //En este if si alguna línea  de texto contiene un asterisco '*' se detien el programa.
                if (input.contains("*")) {
                    //Aqui añadimos el texto que hay antes del asterisco, quitando todo lo que hay despues del asterisco.
                    //La instruccion input.indexOf("*") nos dice donde esta puesto el asterisco.
                    inputText.append(input, 0, input.indexOf("*"));
                    break;  //Por ultimo salimos del bucle porque ya hemos encontrado el asterisco.
                }

                //Si no encontramos el asterisco, agregamos toda la linea al resto del texto.
                inputText.append(input).append("\n");  //Tambien añadimos un salto de linea para separar el texto.
            }

            //Mostramos por pantalla todo el texto que el usuario ha escrito antes del asterisco.
            System.out.println("Texto escrito:");
            System.out.println(inputText.toString());
        } 
        
        catch (Exception e) {
            //Si hay algun error durnate la lectura del texto, mostramos un mensaje explicando el problema.
            System.err.println("Se produjo un error al leer su texto :( " + e.getMessage());
        } 
        
        finally {
            //Por ultimo cerramos el objeto Scanner para liberar los recursos utilizados, independientemente de si se hay un error o no.
            scanner.close();
        }
    }
}