
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

/*funcion que llama los hijos para que muestre su PID y el PID del padre 
    recive un int que indica al hijo que corresponde
    devuelve el PID del hijo que corresponde a travez del getpid y del padre a travez del getppid (ejemplo2 de c)
*/ 
void info_hijos (int hijo_id) {
    printf("Hijo %d PID -> %d, PID del padre -> %d\n", hijo_id, getpid(), getppid());
}

int main() {
    pid_t pid_hijo1, pid_hijo2, pid_hijo3;
    
    // muestro el PID del padre
    printf("PADRE PID del padre -> %d\n", getpid());

    // creo primer hijo
    pid_hijo1 = fork();
    if (pid_hijo1 < 0) {
        perror("Error al crear el primer hijo");
        exit(EXIT_FAILURE);
    } else if (pid_hijo1 == 0) {
        wait(NULL);
        info_hijos(1);
        //pongo exit() para que el hijo una vez creado y mostrado la info se cierre solo
        exit(0);
    }

    // crear segundo hijo
    pid_hijo2 = fork();
    if (pid_hijo2 < 0) {
        perror("Error al crear el segundo hijo");
        exit(EXIT_FAILURE);
    } else if (pid_hijo2 == 0) {
        wait(NULL);
        info_hijos(2);
        exit(0);
    }

    // crear tercer hijo
    pid_hijo3 = fork();
    if (pid_hijo3 < 0) {
        perror("Error al crear el tercer hijo");
        exit(EXIT_FAILURE);
    } else if (pid_hijo3 == 0) {
        wait(NULL);
        info_hijos(3);
        exit(0);
    }
    
    // lo pongoo para esperar a que todos los hijos terminen por si 
    wait(NULL);
    

    return 0;
}