import java.io.BufferedReader;
import java.io.InputStreamReader;

public class cadenaCaracteres
{
    public static void main(String[] args) {

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (in);

        try{
            int salida=0; //Variable para salida de do-while posterior
            String texto=""; // para salida de do-while
            String lectura; // string lectura

            do{

                lectura= br.readLine(); //Lee un string entero que está en el buffer,


                for(int i=0;i<lectura.length();i++){
                    //Recorre el string leido y guarda todo hasta un *
                    if(lectura.charAt(i)=='*'){
                        salida=1;//Encontrar el '*' sale del do-while
                        break;
                    }else{
                        texto+=lectura.charAt(i);
                    }
                }

            }while(salida!=1); //si no encuentra un '*', repetirá el proceso hasta recibir un string que lo contenga

            System.out.println("Total texto almacenado: "+texto);
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
