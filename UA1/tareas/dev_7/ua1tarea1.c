#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
  int numero;
  printf("Introduce un número --> ");
  scanf("%d", &numero); 
  pid_t pid, Hijo_pid; //Declaramos los pid
  pid = fork(); //Creamos el proceso hijo
  if (pid == -1 ) //Comprueba si ha habido algún error en la creación del hijo
  {
    printf("\nNo se ha podido crear el proceso hijo..."); //Mensaje de error
    exit(-1); 
  }
  if (pid == 0 )  //Comprobamos si es el hijo
  {        
    numero += 4; 
    printf("Soy el proceso hijo <---> Mi PID es %d",getpid()); //Mostramos el PID del hijo
    printf("\n El numero del hijo es : %d",numero); //Mostramos el numero del hijo
    exit(0); //El proceso hijo termina
  }
  else    
  { 
    Hijo_pid = wait(NULL); //Con esta línea esperamos a que el hijo termine
    numero -=  5; 
    printf("\n Soy el proceso padre <---> Mi PID es %d",  getpid());  //Mostramos el PID del padre
    printf("\n El numero del padre es : %d",numero); //Mostramos el numero del padre
  }
  exit(1); //El proceso padre termina
}