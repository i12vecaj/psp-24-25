import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.*;

public class Tarea2parte2 {

   
    
    public static void main(String[] args) throws IOException {
        		// Declaracion de la direccion del ejecutable del programa a ejecutar		
 try{
      String executionPath = System.getProperty("user.dir");
      System.out.print("Executing at =>"+executionPath.replace("\\", "/"));
    }catch (Exception e){
      System.out.println("Exception caught ="+e.getMessage());
    }
		// Declaracion de la direccion del ejecutable del programa a ejecutar		
                File directorio = new File("/home/daniel/Escritorio/PROGRAMACION DE S Y P/tarea2parte2DaniFernandez/src/main/java/com/mycompany/tarea2parte2danifernandez");
		//File directorio = new File("/home/daniel/Escritorio/PROGRAMACION DE S Y P/tarea2danifernandez/src/main/java/com/mycompany/tarea2danifernandez");
		// DefiniciAn del ProcessBuilder encargado de localizar el ejecutable
		ProcessBuilder pb = new ProcessBuilder("java", "Tarea2danifernandez.java");	// Estudiar como hacerlo con paquetes: com.ceslopedevega....
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
