#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
    
    pid_t pid = fork();
    int num;
    printf("Introduce un numero: ");
    scanf("%d", &num);
    printf("El numero introducido es: %d\n", num);
        
    if(pid == -1){
        printf("Error a crear el padre");
    }
    if(pid == 0){
        printf("Estamos en el hijo\n");
        num = num - 5;
        printf("El resultado de la resta es%d\n", num);
    }else{
        
        printf("Estamos en el padre\n");
        num = num + 4;
        printf("El numero de la suma es: %d\n", num);
        
    }
    return 1;
}