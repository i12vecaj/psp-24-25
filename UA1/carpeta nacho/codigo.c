//FR1: cree un proceso (existirán en realidad dos procesos, uno padre y el otro hijo) - 1 punto
//FR2: pedirá al usuario una variable - 1 punto
//FR3: el proceso padre restará 5 a dicha variable - 1 punto
//FR4: el proceso hijo le sumará 4 - 1 punto
//FR5: mostrará todos los valores por pantalla - 1 punto
//Implementa el control de errores - 2 puntos

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid; 
    int variable;

    printf("Ingrese un número entero: ");
    scanf("%d", &variable);
    pid = fork();

    if (pid < 0) {
        perror("Error al crear el proceso");
        return 1;
    } else if (pid == 0) { 
        variable += 4;
         printf("soy el hijo mi resultado es %d\n", variable);
    } else {
        variable -=5;
        printf("soy el padre mi resultado es%d\n", variable);
       }
        return 0;
}

