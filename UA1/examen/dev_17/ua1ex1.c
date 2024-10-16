#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main(){

    pid_t pid; //Declaramos la variable pid con tipo pid_t
    int i;

    printf("Aqui tiene la informacion de los procesos hijos:\n\n"); //Creamos fuera este print para que no se repita todo el rato

    for (i = 1; i<=3; i++){ //Dentro del bucle for creamos los procesos hijos
        pid = fork();

    if(pid < 0){ //Aqui hacemos un control de errores, por si los procesos hijo no se crean bien
        printf("Lo sentimos ha habido un error al crear el proceso\n");
        exit(-1);
    }
    
    if(pid == 0){ //Ahora dentro de este if llamamos a la funcion donde mostramos la informacion de cada proceso
        
        informacion(i);
        exit(0);
    }
    }

    for(i = 1; i<=3; i++){ //Ahora metemos dentro de un bucle el wait null para esperar que finalicen los procesos hijos
        wait(NULL);//Aqui esperamos que finalicen los procesos hijos
    }

    printf("\n");
    printf("Y aqui tiene la informacion del proceso padre:\n\n");
    printf("El PID del padre es: %d.",getpid()); //Por ultimo mostramos el PID del padre
    
    return 0;
}

void informacion(int hijos){ //En la funcion le pasamos el parametro hijos
    printf("El hijo numero %d, tiene un PID: %d. Y el PID de su padre es: %d. \n",hijos, getpid(),getppid());//Y aqui mostramos su PID y el PID del padre.
}