class Caracol1 extends Thread {
    final private Carrera carrera;

    public Caracol1(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public void run() {
        correr(1);
    }

    private void correr(int numeroCaracol) {
        try {
            // Esperar la señal para comenzar la carrera
            synchronized (carrera.obtenerBanderita()) {
                carrera.obtenerBanderita().wait();
            }

            // Simular la carrera
            int tiempoCarrera = (int) (Math.random() * 21) + 10; // Entre 10 y 30 segundos
            System.out.println("Caracol " + numeroCaracol + " empezó la carrera.");

            // Simular el tiempo que tarda en correr
            Thread.sleep((long) tiempoCarrera * 1000);

            // Imprimir que ha terminado la carrera
            System.out.println("Caracol " + numeroCaracol + " terminó la carrera en " + tiempoCarrera + " segundos.");

            // Pasar la banderita al siguiente caracol (en orden)
            synchronized (carrera.obtenerBanderita()) {
                int siguienteCaracol = (numeroCaracol % carrera.obtenerNumCaracoles()) + 1;
                System.out.println("Caracol " + numeroCaracol + " pasa la banderita al siguiente caracol: " + siguienteCaracol);
                carrera.obtenerBanderita().notify();
            }

            // Sumar tiempo al total
            carrera.sumarTiempo(tiempoCarrera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}