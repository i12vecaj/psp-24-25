#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int numero;
    
    printf("Introduce una variable entera: ");
    scanf("%d", &numero);

    //Crear un proceso
    pid_t pid = fork();
    
    if (pid < 0) {
        
        //si fork() falla
        perror("Error al crear el proceso");
        return 1;
    }

    if (pid > 0) {
        // Este es el proceso padre
        wait(NULL); 
        printf("Proceso Padre: la variable menos 5 es %d\n", numero, numero -5);
    } else {
        // Este es el proceso hijo
        printf("Proceso Hijo: la variable más 4 es %d\n", numero, numero +4);
        exit(0);    
    }

    return 0;
}
