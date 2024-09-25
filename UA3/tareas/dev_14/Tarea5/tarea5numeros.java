public class tarea5numeros {
    
    
        int base;
        long cuadrado,cubo;
    
        public tarea5numeros(int base, long cuadrado, long cubo) {
            this.base = base;
            this.cuadrado = cuadrado;
            this.cubo = cubo;
        }
        
        public tarea5numeros() {
            base = 0;
            cuadrado = 0;
            cubo = 0;
        }
    
        public void setbase(int base) {
            this.base = base;
        }
    
        public void setCuadrado(long cuadrado) {
            this.cuadrado = cuadrado;
        }
    
        public void setCubo(long cubo) {
            this.cubo = cubo;
        }
    
        public int getbase() {
            return base;
        }
    
        public long getCuadrado() {
            return cuadrado;
        }
    
        public long getCubo() {
            return cubo;
        }
    
        @Override
    
        
            public String toString() {
                return "numeros{" + "\n Base= " + base + "\n Cuadrado=" + cuadrado + "\n Cubo=" + cubo + '}';
            }
        
       
        
    }

