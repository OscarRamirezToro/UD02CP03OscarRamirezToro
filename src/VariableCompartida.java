// Clase que representa el recurso compartido entre ambos hilos
class VariableCompartida {
    // Almacena el número actual que se genera y se consume
    private int valor;

    // Semáforo lógico (flag) para coordinar turnos:
    // true si hay un valor nuevo listo para leer, false si está vacío
    private boolean disponible = false;

    // Método sincronizado para que el productor inserte un dato
    public synchronized void set(int nuevoValor) {
        // Mientras haya un valor pendiente de ser leído por el consumidor, se espera
        while (disponible) {
            try {
                // El hilo productor libera el cerrojo y se queda a la espera
                wait();
            } catch (InterruptedException e) {
                // Restablece el estado de interrupción en caso de corte del hilo
                Thread.currentThread().interrupt();
            }
        }
        // Se guarda el nuevo valor generado
        this.valor = nuevoValor;
        // Marcamos que ya existe un dato listo para consumir
        this.disponible = true;
        // Notificamos al hilo consumidor para que despierte y lo recoja
        notifyAll();
    }

    // Método sincronizado para que el consumidor extraiga el dato
    public synchronized int get() {
        // Mientras no haya un valor nuevo disponible para leer, se espera
        while (!disponible) {
            try {
                // El hilo consumidor libera el cerrojo y se queda a la espera
                wait();
            } catch (InterruptedException e) {
                // Restablece el estado de interrupción en caso de corte del hilo
                Thread.currentThread().interrupt();
            }
        }
        // Marcamos que el dato ha sido consumido y queda libre el hueco
        this.disponible = false;
        // Notificamos al productor para que genere el siguiente número
        notifyAll();
        // Devolvemos el valor leído
        return this.valor;
    }
}