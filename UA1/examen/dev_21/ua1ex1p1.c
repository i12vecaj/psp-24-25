#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>


int main()
{
    pid_t padre;
    int pid_hijo_1, pid_hijo_2, pid_hijo_3;
    
    
    padre = fork();
    
    if(padre == -1){
        printf("No se ha podicrear el proceso hijo.");
    }
    if(padre == 0){
        pid_hijo_1 = getpid();
    }
    else{
        padre = fork();
        if(padre == -1){
            printf("No se ha podicrear el proceso hijo.");
        }
        if(padre == 0){
            pid_hijo_2 = getpid();
        }
        else{
            padre = fork();
            if(padre == -1){
                printf("No se ha podicrear el proceso hijo.");
            }
            if(padre == 0){
                pid_hijo_3 = getpid();
            }
            else{
                printf("Soy el proceso padre mi PID es %d, el de mi hijo 1 es %d, el de mi hijo 2 es %d, el de mi hijo 3 es %d", getpid(), pid_hijo_1, pid_hijo_2, pid_hijo_3);
            }
        }
    }
    
    
    

    return 0;
}