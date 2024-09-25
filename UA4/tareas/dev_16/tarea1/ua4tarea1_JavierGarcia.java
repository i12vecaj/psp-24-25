
//java -cp commons-net-3.9.0.jar ua4tarea1_JavierGarcia.java

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;

/*
 https://sites.google.com/site/nikos3194rfcindex/home/old-school/ftp
 ftp.uv.es
 ftp.unavarra.es
 ftp.rediris.es
 ftp.uma.es
 ftp.udc.es
 ftp.dit.upm.es
 ftp.freenet.de
 
 */
public class ua4tarea1_JavierGarcia {
	public static void main(String[] args) throws SocketException, IOException {

        Scanner sca=new Scanner(System.in);

		FTPClient cliente = new FTPClient();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("A que servidor desea conectarse?");
		String servFTP=sca.nextLine(); // servidor FTP
        System.out.println("");
        System.out.println("--------------------");
		System.out.println("Nos conectamos a: " + servFTP);
		cliente.connect(servFTP);

		// respuesta del servidor FTP
		System.out.print(cliente.getReplyString());
		// codigo de respuesta
		int respuesta = cliente.getReplyCode();

		System.out.println("Respuesta: "+respuesta);
		
		// comprobacion del codigo de respuesta
		if (!FTPReply.isPositiveCompletion(respuesta)) {
			cliente.disconnect();
			System.out.println("Conexion rechazada: " + respuesta);
			System.exit(0);
		}
		// desconexion del servidor FTP
		cliente.disconnect();
		System.out.println("Conexion finalizada.");
	}

}// ..