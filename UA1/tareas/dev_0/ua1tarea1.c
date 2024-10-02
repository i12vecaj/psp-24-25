#include <stdio.h>      
#include <stdlib.h>     
#include <sys/types.h>  
#include <unistd.h>     

int main() {
    int variable;        
    pid_t pid;          

    printf("Por favor, ingrese un numero entero: ");
    if (scanf("%d", &variable) != 1) { 
        fprintf(stderr, "Error: Entrada no valida.\n");
        return 1;
    }

    //crear el proceso fork
 
    pid = fork();

    //no puede crearse

    if (pid < 0) {
        fprintf(stderr, "Error: No se pudo crear el proceso hijo.\n");
        return 1;
    }

    //proceso hijo

    if (pid == 0) { 
        variable += 4;
        printf("Proceso hijo (PID: %d): Valor despues de sumar 4: %d\n", getpid(), variable);
    } else { 
        variable -= 5;
        printf("Proceso padre (PID: %d): Valor despues de restar 5: %d\n", getpid(), variable);
    }

    //proceso padre
    
    if (pid > 0) { 
        wait(NULL); 
        printf("Proceso padre (PID: %d): Valor final: %d\n", getpid(), variable);
    }

    return 0; 
}
