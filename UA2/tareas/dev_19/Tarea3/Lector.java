import java.io.FileNotFoundException;
import java.io.FileReader;

public class Lector extends Thread{
    FileReader reader=null;
    int numero;
    String nombreArchivo,Nombre,documento;

    public Lector(String Nombre,String documento){
        this.Nombre=Nombre;
        this.documento=documento;

    }
    public void run(){
        try{
            nombreArchivo = "src/"+documento+".txt";
            reader = new FileReader(nombreArchivo);
            numero = reader.read();
            long startTime = System.nanoTime();
            while (numero!=-1){
                System.out.print((char)numero);
                numero = reader.read();
            }
            long endTime = System.nanoTime();
            System.out.println("\nTiempo de lectura: "+(endTime-startTime)+" nanosegundos");
        }catch (FileNotFoundException e){
            System.out.println("Archivo no encontrado");
        }catch (Exception e){
            System.out.println("Error al leer el archivo");
        }finally {
            try{
                if(reader!=null) {
                    reader.close();
                }
            } catch (Exception e){
                System.out.println("Error al cerrar el archivo");
            }
        }
        System.out.println("\nHilo "+Nombre+" terminado");
    }

}
