#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  int valor;
    printf("Introduzca un numero entero: ");
     scanf("%d", &valor);
  pid_t pid, Hijo_pid;
  pid = fork();

  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  if (pid == 0 )  //Nos encontramos en Proceso hijo 
  {        
    printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n",  
            getpid(), getppid() );
            valor+=4;
            
  }
  else    //Nos encontramos en Proceso padre 
  { 
   Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
   printf("Soy el proceso padre:\n\t Mi PID es %d, El PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);          
  valor-=5;
 
  
   }
    printf("%d",valor);
   exit(0);
}
