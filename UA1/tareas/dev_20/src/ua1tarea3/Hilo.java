package ua1tarea3;

public class Hilo implements Runnable{

  String[] args;
  int exitCode;

  @Override
  public void run(){
    // Si hay más de 0 argumentos entra
    if(args.length != 0){
         try{
           int valor = Integer.parseInt(args[0]);
           if (valor <0){
             exitCode = 3;
           }else{
             exitCode = 0;
           }
         }catch (Exception e){
           // En caso de que me lance una exepcion que no es un número entonces es un string
           exitCode = 2;
         }
    }else{
      exitCode = 1;
    }

  }
  public int getExitCode() {
    return exitCode;
  }
}
