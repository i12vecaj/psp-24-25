package tarea2parte2_javiergarcia;

import java.io.File;
import java.io.IOException;

public class Tarea2Parte2_JavierGarcia {

	public static void main(String[] args) {
		// Declaramos la direccion del archivo ejecutable del programa a ejecutar		
                
                File archivo=new File("C:\\Users\\Javi\\Desktop\\Tarea2_JavierGarcia\\src\\tarea2_javiergarcia");
                
		
		// Declaramos el ProcessBuilder encargado de localizar el ejecutable
		ProcessBuilder pb = new ProcessBuilder("java", "Tarea2_JavierGarcia.java");	
		
		pb.directory(archivo);
				
                    try {   
                        // Redireccion de la entrada y salida del proceso lanzado a mi mismo
                        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
                        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

                        // ºEjecutamos el proceso anterior
                        Process p = pb.start();
                        // Esperamos a que termine
                        try {
                            p.waitFor();
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
    }
}
    

