class Caracol extends Thread {
    private int numeroCaracol;
    private Carrera carrera;

    public Caracol(int numeroCaracol, Carrera carrera) {
        this.numeroCaracol = numeroCaracol;
        this.carrera = carrera;
    }

    @Override
    public void run() {
        try {
            // Informar que ha alcanzado el punto de espera
            System.out.println("Esperando para comenzar la carrera: Caracol " + numeroCaracol);
            carrera.obtenerBarrera().countDown();

            // Esperar a que todos los caracoles estén listos
            carrera.obtenerBarrera().await();

            // Notificar a todos los caracoles para comenzar la carrera
            synchronized (carrera.obtenerBanderita()) {
                System.out.println("Notificando para comenzar la carrera.");
                carrera.obtenerBanderita().notifyAll();
            }

            // Simular la carrera
            int tiempoCarrera = (int) (Math.random() * 5) + 5; // Entre 5 y 10 segundos
            System.out.println("Caracol " + numeroCaracol + " empezó la carrera.");

            // Simular el tiempo que tarda en correr
            Thread.sleep(tiempoCarrera * 1000);

            // Imprimir que ha terminado la carrera
            System.out.println("Caracol " + numeroCaracol + " terminó la carrera en " + tiempoCarrera + " segundos.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}