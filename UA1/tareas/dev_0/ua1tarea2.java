import java.util.Scanner;
//Creo la clase leerString donde se ejecutará e codigo de lectura de caracteres y la impresión por pantalla de los mismos.
public class leerString {
    //Creo el metodo que ejecutará el programa
    public void lectura(){
        //Creo el objeto "leerCadena" para poder obtener los caracteres que escriba el usuario por teclado usando el "System.in"
        Scanner leerCadena = new Scanner(System.in);
        //Creo el objeto "letras" como un StringBuilder para poder gestionar los caracteres de la cadena de forma cómoda
        StringBuilder letras = new StringBuilder();
        //Creo un bucle while que se asegura de leer la cadena y ejecutar el bucle for
        while(true){
            //Lectura de la cadena para asignarla como un String y poder igualar valores en el bucle for
            String cadena = leerCadena.nextLine();
            //Creo un bucle que recorra la cadena caracter por caracter y los asigne a la variable letra uno por uno
            for (char letra : cadena.toCharArray()){
                //Concateno con el metodo append de la clase StringBuilder el caracter que esté dentro de letra al objeto letras
                letras.append(letra);
                //Comprobación de si el último caracter leido es un *
                if (letra == '*'){
                    //en caso de SI ser un * se imprime por pantalla lo almacenado hasta el momento en "letras" que debería acabar en el *
                    System.out.println(letras);
                    //Se finaliza el programa
                    return;
                }
            }
        }
    }
}
