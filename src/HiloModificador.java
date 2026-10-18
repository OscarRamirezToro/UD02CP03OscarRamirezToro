// Hilo encargado de generar y escribir los números (Productor)
class HiloModificador extends Thread {
    // Referencia a la instancia de la variable compartida
    private final VariableCompartida variable;

    // Constructor para inyectar la instancia común
    public HiloModificador(VariableCompartida variable) {
        this.variable = variable;
    }

    // Código que ejecutará el hilo al iniciarse
    @Override
    public void run() {
        // Bucle para generar los números del 0 al 99
        for (int i = 0; i < 100; i++) {
            // Inserta el número actual en el recurso compartido
            variable.set(i);
        }
    }
}