// Clase principal donde se lanzan las hebras
public class Principal {
    // Método de entrada del programa
    public static void main(String[] args) {
        // Se crea el objeto único que compartirán ambas hebras
        VariableCompartida recurso = new VariableCompartida();

        // Se crea el hilo que modifica los valores pasando el objeto compartido
        HiloModificador escritor = new HiloModificador(recurso);
        // Se crea el hilo que lee los valores pasando el mismo objeto
        HiloLector lector = new HiloLector(recurso);

        // Se inicia la ejecución concurrente del hilo lector
        lector.start();
        // Se inicia la ejecución concurrente del hilo modificador
        escritor.start();

        try {
            // El hilo principal espera a que termine el escritor
            escritor.join();
            // El hilo principal espera a que termine el lector
            lector.join();
        } catch (InterruptedException e) {
            // Manejo de interrupción del hilo principal
            Thread.currentThread().interrupt();
        }
    }
}