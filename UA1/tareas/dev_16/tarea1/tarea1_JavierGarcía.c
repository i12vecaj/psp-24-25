#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
   pid_t proceso, proceso2;//Se crean las derivadas
   int num;
   
   printf("Introduce un numero");//se le pide un numero al usuario
   scanf("%d",&num);
   
   proceso=fork();
   
   if(proceso==-1){//Si ocurre un error el programa termina
       printf("ERROR, No se ha creado al proceso hijo");
       exit(-1);  
   }
   if(proceso==0){//se pone el calculo a realizar en el proceso hijo
       num=num+4;

   }
   else{//se pone el calculo a realizar en el proceso padre
       wait(NULL);
       num=num-5;
   }
  
printf("Resultado: %d\n",num);//Se imprimen los resultados en pantalla
   exit(0);
}