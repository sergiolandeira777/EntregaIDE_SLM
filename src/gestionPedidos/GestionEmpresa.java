package gestionPedidos;

import java.util.ArrayList;
import java.util.List;

public class GestionEmpresa {
    private List<Repartidor> listaRepartidores;

    public GestionEmpresa() {
        this.listaRepartidores = new ArrayList<>();
    }

    // Método para registrar repartidores en el sistema
    public void registrarRepartidor(Repartidor r) {
        listaRepartidores.add(r);
    }

    // ASIGNACIÓN AUTOMÁTICA: Busca el primer repartidor disponible
    public boolean asignarRepartidorAutomatico(Pedido pedido) {
        for (Repartidor r : listaRepartidores) {
            if (r.getEstadoActual() == EstadoRepartidor.DISPONIBLE) {
                // Usamos el método de Pedido para asociarlo
                boolean asignado = pedido.asignarConductor(r);
                if (asignado) {
                    System.out.println("[SISTEMA] Asignado automáticamente el repartidor: " + r.getNombre());
                    return true;
                }
            }
        }
        System.out.println("[SISTEMA] No se pudo asignar: No hay repartidores disponibles en este momento.");
        return false;
    }
}
