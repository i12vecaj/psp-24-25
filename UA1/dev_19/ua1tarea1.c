#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(){
pid_t pid; // se crea el proceso 
int num;

printf("introduce un numero\n");
scanf("%d",&num);//se introduce el int por parte del usuario
pid=fork();//llamamos ala funcion para crear los diferentes casos de procesos

if(pid==-1){//se controla que el proceso no sea valido
  printf("Error al crear el proceso\n");
  exit(EXIT_FAILURE);
}
if(pid==0){// opera el proceso hijo
  num=num+4;
  printf("El numero del proceso hijo es: %d\n",num);
}else{// opera el proceso padre
  num=num-5;
  printf("El numero del proceso padre es: %d\n",num);
}
return 0;
}