

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author daniel fernandez
 */
public class Tarea3parte2 {
    
    public static void main(String[] args) {
        // Declaracion de la direccion del ejecutable del programa a ejecutar		
 try{
      String executionPath = System.getProperty("user.dir");
      System.out.print("Executing at =>"+executionPath.replace("\\", "/"));
    }catch (Exception e){
      System.out.println("Exception caught ="+e.getMessage());
    }
		// Declaracion de la direccion del ejecutable del programa a ejecutar		
                File directorio = new File("/home/daniel/Escritorio/PROGRAMACION DE S Y P/Tarea3parte1/src/main/java/com/mycompany/tarea3parte1");
		//File directorio = new File("");
		// DefiniciAn del ProcessBuilder encargado de localizar el ejecutable
		ProcessBuilder pb = new ProcessBuilder("java", "Tarea3parte1.java");	// Estudiar como hacerlo con paquetes: com.ceslopedevega....
		// Esta sentencia debe estar cuadrada con la ruta en la que se encuentran los ejecutables compilados: .class, .jar o lo que sea
		pb.directory(directorio);
				
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// El proceso se ejecuta			
			Process p = pb.start();
                        System.out.println("Proceso iniciado");
                    
			// Debemos esperar a la finalizacion del programa/proceso lanzado.
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
