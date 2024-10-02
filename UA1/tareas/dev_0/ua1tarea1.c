#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t pid;
    int numerointroducido;
    
    printf("Introduce un numero entero: \n");
    scanf("%d", &numerointroducido);
    
    pid = fork();

    if (pid == -1)  // Error al crear el proceso hijo
    {
        printf("No se ha podido crear el proceso hijo ;( \n");
        exit(-1);       
    }
    if (pid == 0)   // Proceso hijo
    {        
        numerointroducido += 4;  // El proceso hijo suma 4
        printf("Soy el proceso hijo\n\tMi PID es %d, sumado 4 al número: %d.\n", getpid(), numerointroducido);    
    }
    else            // Proceso padre
    { 
        numerointroducido -= 5;  // El proceso padre resta 5
        printf("Soy el proceso padre\n\tMi PID es %d, restando 5 al número: %d.\n", getpid(), numerointroducido);
    }
    exit(0);
}