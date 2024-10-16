#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    int pid, pid1, pid2;
  
    
    // La variable pid almacena el valor devuelto del fork() system call
    pid = fork();
  
    // Si fork() devuelve 0 indica que es un hijo 
    if (pid == 0) {
        printf("child[1] --> pid = %d and ppid = %d\n",
               getpid(), getppid());
    }
  
    else {
        pid1 = fork();
        // segundo hijo.
        if (pid1 == 0) {
            printf("child[2] --> pid = %d and ppid = %d\n",
                   getpid(), getppid());
        }
        else {
            pid2 = fork();
            if (pid2 == 0) {
                //tercer hijo.
                printf("child[3] --> pid = %d and ppid = %d\n",
                       getpid(), getppid());
            }
  
            //Si el valor retornado por el fork() es >0
            else {
                //Este código sólo lo ejecutará el Padre
                printf("parent --> pid = %d\n", getpid());
            }
        }
    }
  
    return 0;
}