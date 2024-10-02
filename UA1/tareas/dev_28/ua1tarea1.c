#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h> // funcion para el contro del errores de proceso.

//Asignamos nombre 
int main() {
    pid_t pid, Hijo_pid; 
    int numero;  
    
 printf("Porfavor introduzca un numero: ");  
    if (scanf("%d", &numero) != 1) {  
        printf("ERROR este caracter no es valido\n"); 
        exit(-1);
    }

    // Creamos el proceso padre y hijo
    pid = fork();
    if (pid == -1) { 
        perror("Habido un error al crear el proceso Intentelo denuevo gracias");  
        exit(-1);
    }

//Ahora abajo hare el proceso Hijo que suma 4 al numero que introduzcamos
    if (pid == 0) {  
        numero += 4;  
        printf("El proceso hijo sumara 4 acontinuacion: %d\n", numero); 
        exit(0); 

//Acontinuacion haremos que termine totalmente el proceso hijo para comenzar con el del padre.
    } else {  
        Hijo_pid = wait(NULL);
        if (Hijo_pid == -1) { 
            perror("Error en wait"); 
            exit(-1);
        }

//Hacemos proceso padre que en este caso haremos que reste 5 al numero que introduzca el usuario.
    numero -= 5;
     printf("El proceso del padre es :  %d\n", numero); 
    printf("Numero del proceso padre %d terminó.\n", Hijo_pid); 

return 0;
}