/**
 * @file        MiembroToken.java
 *
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        14/12/2022
 * @author      Nombre Apellidos Miembro 1 - github@email (dev_A)
 * @author      Nombre Apellidos Miembro 2 - github@email (dev_B)
 * @author      Nombre Apellidos Miembro 3 - github@email (dev_C)
 * @author      Nombre Apellidos Miembro 4 - github@email (dev_D)
 *
 * @note        ¡Pero qué diablos!
 * @note        https://docs.google.com/presentation/d/e/2PACX-1vQzxbSl2IXLxelLggksAWEQd2tDT-5sNqETQCpIHCnNBqjoSmbvlJdCcOfu_rjQLz_BN6lsoOjimqSO/pub?start=false&loop=false&delayms=3000
 */
import java.io.*;
import java.net.*;

/*
* @class    MiembroToken
* @brief    Clase para simular el comportamiento de una red token ring bidireccional con hilos
*
* @todo FR1 [5 puntos]: Implementa la clase MiembroToken con la funcionalidad descrita previamente. Para comprobar la correcta ejecución del sistema, la clase MiembroToken debe imprimir por pantalla la suficiente información para ver el estado de cada Miembro de la red.
* @todo FR2 [2,5 puntos]: Mejora la clase MiembroToken para poder ejecutar su funcionalidad, en este caso, dormir una cantidad de tiempo determinada, como un hilo.
* @todo FR4 [1 punto]: Mejora la clase MiembroToken para crear una red token ring de anillo doble, es decir, se puede tener otro token en otro anillo virtual, en sentido contrario.
*/

public class MiembroToken
{
    /* @brief       Classic java main, starting execution
     * @param       arg[0] = [id]
     * @param       arg[1] = [puerto]
     * @param       arg[2] = [token_al_inicio]
     * @param       arg[3] = [soy_el_ultimo]
     */
    
    static class Hilo extends Thread{
        String[] args;
        int puerto;
        int puertoEnvio;
        int id;
        boolean token;
        Socket Envio;
        ServerSocket Server;
        Socket Escucha;

        public Hilo(String args[]) throws IOException {
            this.puerto = Integer.parseInt(args[1]);;
            
            if (args[3].equals("no")){
            this.puertoEnvio = puerto+1;
            }else this.puertoEnvio = 10000;
            
            this.id = Integer.parseInt(args[0]);
            
            this.token = args[2].equals("yes");
            
            this.Server = new ServerSocket(puerto);
            }
        
        void escuchar() throws IOException{

            System.out.println("Escuchando en " + puerto);
            Escucha = Server.accept();
            
            token = true;
        }
        
        void trabajar() throws InterruptedException, IOException{
            System.out.println("*trabaja en andaluz*");
            Thread.sleep(id*1000);
            enviar();
            token = false;
        }
        
        void enviar() throws IOException{
            System.out.println("Enviando a "+puertoEnvio+" en localhost");
            Envio = new Socket("localhost", puertoEnvio);
            Envio.close();
        }
        
        public void run(){
            while(true){
            try{
            if(token){
                trabajar();
                System.out.println("Ya no tengo el token\n\n");
            
            }else{
                escuchar();
                System.out.println("Ahora tengo el token");
            }
            }catch(Exception e){
                System.out.println("Error al iterar en run");
            }
        }
    }
    }
public static void main(String[] args) throws IOException, InterruptedException {
        
        Hilo hilo = new Hilo(args);
        
        System.out.println("ID: "+hilo.id);
        
        hilo.start();
    } // End of main()
}
// End of class