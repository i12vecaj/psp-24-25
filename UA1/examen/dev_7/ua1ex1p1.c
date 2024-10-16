
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

void mostrarPid(int numeroHijo, int pidHijo, int pidPadre){ //Esta función recibe 3 número enteros que son: el número del hijo, su pid y el pid del padre. Luego los muestra por pantalla
    printf("- Soy el hijo %d, mi PID es %d, y el de mi padre es %d \n", numeroHijo, pidHijo, pidPadre);
}

void mensajeError(){ //Esta funcion es para ahorra código a la hora de que muestre un error
    printf("-----------------------------------------------------------------\n");
    printf("Parece que no se ha podido crear el proceso, vuele a intentarlo.\n");
    printf("-----------------------------------------------------------------\n");
}



//Explicación:
//Una vez iniciamos el primer proceso hijo, si queremos ya declarar más, 
//pero que todos pertenezcan al mismo padre los tenemos que declarar en el else,
//puesto que todo lo que incluyamos ahí pertenece al proceso padre.
//
int main()
{
    int contador = 1;
      pid_t pidProceso1, pidProceso2, pidProceso3 ; //Declaramos los pid, declaro 3 para orientarnos mejor a la hora de desarrollar
      pidProceso1 = fork(); //Inicio el proceso
      if(pidProceso1 == -1){ //Si falla en crearse el proceso da mensaje de error
          mensajeError();
      }
      
      if(pidProceso1 == 0){
            mostrarPid(contador, getpid(), getppid()); //Mostramos el pid del hijo 1
      }
      else
      {
          wait(NULL);//Esperamos a que el hijo 1 termine
          contador = contador + 1; 
          
          pidProceso2 = fork(); //Creamos el segundo proceso hijo
          if(pidProceso2 == -1){
            mensajeError();
          }
          
          if(pidProceso2 == 0){ //Entramos al proceso hijo 2
                mostrarPid(contador, getpid(), getppid()); //Mostramos su pid y el del padre
          }
          else
          {
            wait(NULL); //Esperamos a que el hijo 2 termine
            contador = contador + 1;
            pidProceso3 = fork(); //Creamos el tercer proceso hijo
            
            if(pidProceso3 == -1){
                mensajeError();
            }
            
            if(pidProceso3 == 0){//Entramos al proceso hijo 3
                mostrarPid(contador, getpid(), getppid());  //Mostramos su pid y el del padre
            }
            else
            {
                wait(NULL); //Esperamos a que termine el proceso hijo 3
                printf("- Soy el programa padre y mi PID es %d \n", getpid()); //Mostramos el pid del padre de todos los procesos hijos
            }
                
          }
      }
    

    return 0;
}