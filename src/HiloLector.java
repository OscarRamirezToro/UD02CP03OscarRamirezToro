// Hilo encargado de leer y mostrar los números (Consumidor)
class HiloLector extends Thread {
    // Referencia a la misma instancia compartida
    private final VariableCompartida variable;

    // Constructor para inyectar la instancia común
    public HiloLector(VariableCompartida variable) {
        this.variable = variable;
    }

    // Código que ejecutará el hilo al iniciarse
    @Override
    public void run() {
        // Bucle para leer exactamente los 100 valores generados
        for (int i = 0; i < 100; i++) {
            // Obtiene el valor sincronizado
            int valorLeido = variable.get();
            // Imprime por consola el número obtenido
            System.out.println("Valor recogido: " + valorLeido);
        }
    }
}