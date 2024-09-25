/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class EjecutarProgramas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Introduzca el numero de miembros");
        int numeroMiembros = entrada.nextInt();
        String command = "cmd.exe /c start "+"java MiembroToken.java";
        for(int i = 2; i<=numeroMiembros-1;i++){
            try {
                Process child = Runtime.getRuntime().exec(command+" "+i+" "+(10000+i-1)+" no no");
            } catch (IOException ex) {
                Logger.getLogger(EjecutarProgramas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
                Process child = Runtime.getRuntime().exec(command+" "+numeroMiembros+" "+(10000+numeroMiembros-1)+" no yes");
            } catch (IOException ex) {
                Logger.getLogger(EjecutarProgramas.class.getName()).log(Level.SEVERE, null, ex);
            }
           

        try {
                Process child = Runtime.getRuntime().exec(command+" "+1+" "+(10000)+" yes no");
            } catch (IOException ex) {
                Logger.getLogger(EjecutarProgramas.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
}
