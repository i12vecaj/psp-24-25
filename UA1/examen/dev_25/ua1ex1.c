#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

//Creo la función donde se muestra el PID del padre y el de los hijos.
void hijo(int i){
    printf("Hijo %d con PID %d y PID del padre %d\n", i, getpid(), getppid());
}

int main (){
    pid_t pid;
    int i;

    //Bucle donde se crean los 3 hijos.
    for (i = 1; i <= 3; i++){
            pid = fork();

            //Si el resultado es -1 ocurre un error y no crea los procesos hijos.
            if (pid == -1){
                printf("No se ha podido crear el proceso hijo");
                exit(-1);
            }
            //Si el resultado es 0 si crea los procesos hijos y se llama a la función definida antes para que los muestre.
            if (pid == 0){
                hijo(i);
                exit(0);
            }
    }

 //El proceso padre espera hasta que los procesos hijos hayan acabado y una vez acaba el programa finaliza muestra solo el PID del padre.    
    if (pid > 0){

        for(i = 1; i<=3; i++){
            wait(NULL);
        }

        printf("El programa padre ha finalizado, este sería su PID: %d\n", getpid());
    }
}