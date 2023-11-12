class Carrera {
    private static final int NUM_CARACOLES = 4;
    private static final Object banderita = new Object();
    private int tiempoTotal = 0;

    public int obtenerNumCaracoles() {
        return NUM_CARACOLES;
    }

    public Object obtenerBanderita() {
        return banderita;
    }

    public synchronized void sumarTiempo(int tiempo) {
        tiempoTotal += tiempo;
    }

    public int obtenerTiempoTotal() {
        return tiempoTotal;
    }

    public void iniciarCarrera() {
        System.out.println("BIENVENIDOS A LA CARRERA DE CARACOLES");
        System.out.println("_____________________________________");

        // Crear los caracoles
        Caracol1 caracol1 = new Caracol1(this);
        Caracol2 caracol2 = new Caracol2(this);
        Caracol3 caracol3 = new Caracol3(this);
        Caracol4 caracol4 = new Caracol4(this);

        // Iniciar la carrera
        caracol1.start();
        caracol2.start();
        caracol3.start();
        caracol4.start();

        System.out.println("Notificando para comenzar la carrera.");

        // Notificar al primer caracol para comenzar la carrera
        synchronized (obtenerBanderita()) {
            obtenerBanderita().notify();
        }

        System.out.println("Esperando a que todos los caracoles terminen.");

        // Esperar a que todos los caracoles terminen
        try {
            caracol1.join();
            caracol2.join();
            caracol3.join();
            caracol4.join();

            System.out.println("Tiempo total de la carrera: " + obtenerTiempoTotal() + " segundos.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}