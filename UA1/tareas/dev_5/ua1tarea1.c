#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
  int variable;
  pid_t pid, Hijo_pid;
  pid = fork();

printf("introduce un numero");
  if scanf(  ("%d" variable !=1)){
    printf("Error en la introducción de datos, introduce un número entero.\n");
    exit(EXIT_FAILURE);


  }

  if (pid <0 ) 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(EXIT_FAILURE);      
  }
 else if (pid == 0 )  
  {        
    printf("Soy el proceso hijo: \n",) 
    variable += 4;
    printf("asi que da si le sumamos 4: %d\n " ,variable);
    exit(EXIT_FAILURE);
        	
  }
  else    
  { 
   
   printf("Soy el proceso padre;:\n");     
   variable -= 5;
   printf("asi que da si le restamos 5: %d\n " ,variable);
    wait(NULL); 
   printf("Proceso padre después de que el hijo terminó.\n");     
   }
   return(0);
}

