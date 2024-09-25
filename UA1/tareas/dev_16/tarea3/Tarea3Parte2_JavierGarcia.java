package tarea3parte2_javiergarcia;

import java.io.IOException;

public class Tarea3Parte2_JavierGarcia {

    
    public static void main(String[] args) {
        
    //Creamos el ProcessBuilder que contiene el archivo que queremos ejecutar
		ProcessBuilder pb = new ProcessBuilder("java","-jar", "C:\\Users\\Javi\\Desktop\\Tarea3Parte1_JavierGarcia\\src\\tarea3parte1_javiergarcia","9");
		
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// Ejecutamos el proceso			
			Process p = pb.start();
                        

			// Debemos esperar a la finalizacion del programa/proceso lanzado.
			try {
				p.waitFor();
                                //Muestra lo que el programa abierto devuelve con System.exit()
                                System.out.println("Devolución: "+p.exitValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
    }
    
}
