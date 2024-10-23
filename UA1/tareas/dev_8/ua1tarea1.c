#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
    
    pid_t pid;
    int num;

    printf("introduce un numero: ");
    scanf("%d", &num);
    printf("el numero introducido es: %d\n", num);

    // jose david el error lo tenia en que pedia el numero despues de hacer el fork cuando lo he situado despues ya funciona correctamente
    pid = fork();

    if(pid == -1){
        printf("error al crear el proceso hijo\n");
        return 1;
    }

    if(pid == 0){
        printf("estamos en el hijo\n");
        num = num - 5;
        printf("el resultado de la resta es: %d\n", num);
    } else {
        wait(NULL);
        printf("estamos en el padre\n");
        num = num + 4;
        printf("el resultado de la suma es: %d\n", num);
    }
    
    return EXIT_SUCCESS;

}
