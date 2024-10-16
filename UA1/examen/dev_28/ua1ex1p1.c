#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

void la_funcion_hijo(int numero_hijo); // antes de declaramos la funcion antes

int main() {
    
    pid_t este_pid_hijo;
    int i;

    for (i = 1; i <= 3; i++) {     //aqui creamos los tres hijos
        este_pid_hijo = fork();  // se crea proceso hijo 

        if (este_pid_hijo < 0) {
            perror("el proceso hijo no se creo"); // error al crear el proceso
            exit(EXIT_FAILURE);
        } else if (este_pid_hijo == 0) { 
            // este es el codigo del hijo
            la_funcion_hijo(i);
            exit(EXIT_SUCCESS); // terminar el proceso hijo
        }
    }

    for (i = 0; i < 3; i++) {    // este codigo pertnece al padre pero tiene que terminar el proceso del hijo para poder empezar.
        wait(NULL);  // espera a que los hijos terminen
    }

    printf("padre:\n  pid que pertenece del padre: %d\n", getpid());   // una vez que finalize lo hijos se imprime el pid del padre

    return 0;
}

void la_funcion_hijo(int numero_hijo) {
    printf("hijo %d:\n", numero_hijo);        // esta es funcion para que los hijo impriman la funcion 
    printf("tiene como pid el hijo: %d\n", getpid());
    printf("tiene como pid el padre: %d\n\n", getppid());
}

