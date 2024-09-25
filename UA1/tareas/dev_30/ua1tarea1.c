#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
    int valor_usuario, valor_hijo, valor_padre;
    // Le pedimos un valor al usuario

    printf("Digame un numero: ");
    scanf("%d",&valor_usuario);
    //Creamos al proceso hijo haciendo uso de la funcion fork;
    pid_t pid = fork();
    // En el caso de que nos de -1 en la creación del proceso nos saltará error 
    if (pid == -1){
        printf("No se ha podido crear al hijo\n\n ");
        exit(-1);
    }
    if (pid == 0 ){
        // Entremos al proceso del hijo
        printf("Estamos en el hijo.\n\n");
        valor_hijo=valor_usuario+4;
        printf("El  hijo ha sumado 4 al valor del usuario %d dando como resultado %d.\n\n", valor_usuario,valor_hijo);


    }else{
        // Entramos al proceso del padre
        printf("Estamos en el padre.\n\n ");
        valor_padre=valor_usuario-5;
        printf("El padre ha restado 5 al valor del usuario %d dando como resultado %d.\n\n", valor_usuario,valor_padre);
    }

    return 1;
}