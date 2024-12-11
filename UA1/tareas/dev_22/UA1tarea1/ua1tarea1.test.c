#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    return 0;
}

void test_handle_input_zero() {
    int pipefd[2];
    pipe(pipefd);
    pid_t pid = fork();

    if (pid == 0) {
        // Child process
        close(pipefd[0]); // Close unused read end
        dup2(pipefd[1], STDOUT_FILENO); // Redirect stdout to pipe
        close(pipefd[1]); // Close write end after redirect

        // Simulate input value 0
        FILE *input = fopen("input.txt", "w");
        fprintf(input, "0\n");
        fclose(input);
        freopen("input.txt", "r", stdin);

        execl("./ua1tarea1", "./ua1tarea1", NULL);
        exit(0);
    } else {
        // Parent process
        close(pipefd[1]); // Close unused write end
        wait(NULL); // Wait for child process to finish

        char buffer[128];
        read(pipefd[0], buffer, sizeof(buffer));
        close(pipefd[0]);

        // Check output for both parent and child processes
        if (strstr(buffer, "Soy el proceso PADRE:\n\t Mi valor habiendo restando 5 es de -5.\n") &&
            strstr(buffer, "Soy el proceso HIJO \n\t Mi valor habiendo sumado 4 es de -1.\n")) {
            printf("Test passed: Correctly handled input value of 0\n");
        } else {
            printf("Test failed: Incorrect handling of input value of 0\n");
        }
    }
}
