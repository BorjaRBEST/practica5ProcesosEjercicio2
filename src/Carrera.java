import java.util.concurrent.CountDownLatch;

class Carrera {
    private static final int NUM_CARACOLES = 4;
    private static final Object banderita = new Object();
    private CountDownLatch barrera = new CountDownLatch(NUM_CARACOLES);

    public int obtenerNumCaracoles() {
        return NUM_CARACOLES;
    }

    public Object obtenerBanderita() {
        return banderita;
    }

    public CountDownLatch obtenerBarrera() {
        return barrera;
    }

    public void iniciarCarrera() {
        // Crear los caracoles
        Caracol[] caracoles = new Caracol[obtenerNumCaracoles()];
        for (int i = 0; i < obtenerNumCaracoles(); i++) {
            caracoles[i] = new Caracol(i + 1, this);
        }

        // Iniciar la carrera
        for (int i = 0; i < obtenerNumCaracoles(); i++) {
            caracoles[i].start();
        }

        // Esperar a que todos los caracoles terminen
        for (int i = 0; i < obtenerNumCaracoles(); i++) {
            try {
                caracoles[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}