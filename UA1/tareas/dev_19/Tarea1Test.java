//package ua1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Tarea1Test {

    private static Tarea1 tarea1 = new Tarea1();   
/*
    public static void main(String[] args) 
    {
        tarea1.hello();    
    }
    */

    @Test
    void addition() {
        assertEquals(2, tarea1.add(1,1));
    }

/*
    @Test
    void checkHello() {
        assertEquals("Paco", tarea1.hello());
    }
*/
  
}
