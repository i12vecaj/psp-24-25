
import java.io.File;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alvar
 */
public class Tarea2 {
    
    public static void main(String[] args) {
		// Declaracion de la direccion del ejecutable del programa a ejecutar		
		//File directorio = new File(".\\bin");
		
		// DefiniciAn del ProcessBuilder encargado de localizar el ejecutable
		File directorio = new File("build\\classes\\");
		// DefiniciAn del ProcessBuilder encargado de localizar el ejecutable
		ProcessBuilder pb = new ProcessBuilder("java", "Lanzar");	// Estudiar como hacerlo con paquetes: com.ceslopedevega....
		pb.directory(directorio);
				
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// El proceso se ejecuta			
			Process p = pb.start();

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
