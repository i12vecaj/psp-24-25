class SesorTemperatura {

    public void run() {
        while (true) {
            double temperatura = obtenerTemperatura();
            System.out.println("Temperatura: " + temperatura);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private double obtenerTemperatura() {
        return Math.random() * 30 + 15;        // Cone sto podria generar una temperatura aleatoria (al final no ahcia falta el randomificador ese tan grande)
    }
}