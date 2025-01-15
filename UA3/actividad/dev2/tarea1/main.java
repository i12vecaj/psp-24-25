import java.net.*;
import java.util.Objects;
import java.util.Scanner;

// Para realizar esta actividad me he basado en el ejemplo "PSP_UA3_Ejemplo_2_URL_1.java" del repositorio ya que
// tenía las funcionesque necesitaba y solo tenía que modificar la entrada de URLS
// ya que el ejemplo tenía URLS fijas y me era necesario que entrasen por la consola

public class Main
{
    public static void main(String[] args) {
        URL url;
        Scanner lectura = new Scanner(System.in);
        try
        {
            int i=0;
            while (i<1){
                System.out.println("Escriba una URL, no olvide añadir en la URL el protocolo http o https");
                String urlLeida=lectura.nextLine();
                if (!Objects.equals(urlLeida, "localhost")){
                    url = new URL(""+urlLeida);
                    Visualizar(url);
                }
                else {
                    System.out.println("El programa se ha detenido ya que se ha insertado localhost");
                }
            }
        }
        catch (MalformedURLException e) {	System.out.println(e);}
    }

    private static void Visualizar(URL url)
    {
        System.out.println("\tURL completa: " + url.toString());
        System.out.println("\tgetProtocol(): " + url.getProtocol());
        System.out.println("\tgetHost(): " + url.getHost());
        System.out.println("\tgetPort(): " + url.getPort());
        System.out.println("\tgetFile(): " + url.getFile());
        System.out.println("\tgetUserInfo(): " + url.getUserInfo());
        System.out.println("\tgetPath(): " + url.getPath());
        System.out.println("\tgetAuthority(): " + url.getAuthority());
        System.out.println("\tgetQuery(): " + url.getQuery());
        System.out.println("\tgetDefaultPort(): "+ url.getDefaultPort());
        System.out.println("==================================================");
    }
}
