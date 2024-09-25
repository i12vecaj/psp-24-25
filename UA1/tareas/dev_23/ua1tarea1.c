#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
void main() {
  pid_t pid, Hijo_pid;
  int numero;
  printf("Introduzca un numero: ");
  scanf("%d", &numero);
  
  pid = fork();
  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  if (pid == 0 )  //Nos encontramos en Proceso hijo 
  { 
    numero +=4;
    printf("Soy el proceso hijo y le he sumado cuatro a tu numero y me ha dado como resultado: %d\n", numero);	
  }
  else    //Nos encontramos en Proceso padre 
  { 
   Hijo_pid = wait(NULL); //espera la finalización del proceso hijo
   numero -=5;
   printf("Soy el proceso padre y le he restado cicnco a tu numero y me ha dado como resultado: %d", numero);          
   }
   exit(0);
}