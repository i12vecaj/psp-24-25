#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main () {

    //Declaramos las variables de pid, num, resultado hijo y padre.
    pid_t pid; 
    int num;  
    int result_hijo;
    int result_padre;

    //De decimos al usuario que introduzca un número.
    printf("Introduce un número: ");
    scanf("%d", &num);

   //Creamos el proceso.
   pid = fork();

    //Esto es para saber si se ha podido crear el proceso.
  if (pid == -1 )
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }

    //Aquí si se ha creado el proceso monstrará el resultado del proceso hijo y del proceso padre.
    if (pid == 0) {
        result_hijo = num + 4;
        printf("El resultado del proceso hijo es: %d + 4 = %d \n", num, result_hijo);
        exit(EXIT_SUCCESS);
    }
    else {
        result_padre = num - 5;
        printf("El resultado del proceso padre es: %d - 5 = %d\n", num,result_padre);
        exit(EXIT_SUCCESS);}

}