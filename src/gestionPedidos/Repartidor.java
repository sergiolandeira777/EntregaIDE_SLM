package gestionPedidos;

import java.util.ArrayList;
import java.util.List;

public class Repartidor extends Usuario {
    private String zonaDeReparto;
    private EstadoRepartidor estadoActual;
    private List<Pedido> pedidosAsignados;

    public Repartidor(String nombre, String email, String telefono, String zonaDeReparto) {
        super(nombre, email, telefono);
        this.zonaDeReparto = zonaDeReparto;
        this.estadoActual = EstadoRepartidor.DISPONIBLE;
        this.pedidosAsignados = new ArrayList<>();
    }

    public String getZonaDeReparto() { return zonaDeReparto; }
    
    public EstadoRepartidor getEstadoActual() { return estadoActual; }
    
    public void setEstadoActual(EstadoRepartidor estadoActual) {
        this.estadoActual = estadoActual;
    }

    public List<Pedido> getPedidosAsignados() {
        return pedidosAsignados;
    }

    public void asignarPedido(Pedido p) {
        this.pedidosAsignados.add(p);
        if (this.pedidosAsignados.size() >= 3) {
            this.setEstadoActual(EstadoRepartidor.OCUPADO);
        }
    }

    public void finalizarPedido(Pedido p) {
        this.pedidosAsignados.remove(p);
        if (this.estadoActual == EstadoRepartidor.OCUPADO && this.pedidosAsignados.size() < 3) {
            this.setEstadoActual(EstadoRepartidor.DISPONIBLE);
        }
    }
}