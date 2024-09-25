package tarea3parte1_javiergarcia;


public class Tarea3Parte1_JavierGarcia {

    
    public static void main(String[] args) {
        //Si los argumentos son >1, devolverá 1
        if(args.length<1){
            System.exit(1);
        }else{
            try{
                //Si consigue transformar el argumento a string devolverá 0
                int argumentoInt = Integer.parseInt(args[0]);
                
                    if(argumentoInt < 0){
                    //Si el numero es menor que 0 devuelve 3
                    System.exit(3);
                    }else System.exit(0);
                    
                }catch(Exception e) {
                //Si al convertir el argumento a entero da error devuelve 2
                    System.exit(2);
                }
            
	}
        
    }
    
}
