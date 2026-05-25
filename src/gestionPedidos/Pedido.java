package gestionPedidos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
    private String identificador;
    private LocalDateTime fechaCreacion;
    private EstadoPedido status;
    private Cliente clienteProp;
    private Repartidor mensajero;
    private Set<Producto> cesta;
    private double importeTotal;

    public Pedido(String identificador, Cliente clienteProp) {
        this.identificador = identificador;
        this.clienteProp = clienteProp;
        this.fechaCreacion = LocalDateTime.now();
        this.status = EstadoPedido.PENDIENTE;
        this.mensajero = null;
        this.cesta = new HashSet<>();
        this.importeTotal = 0.0;
        
        this.clienteProp.registrarPedido(this);
    }

    public boolean agregarProducto(Producto prod) {
        if (cesta.add(prod)) {
            importeTotal += prod.getPrecioUnitario();
            return true;
        }
        return false; 
    }

    public boolean asignarConductor(Repartidor r) {
        if (cesta.isEmpty()) {
            System.out.println("No se puede asignar repartidor: El pedido no tiene productos.");
            return false;
        }
        if (r.getEstadoActual() != EstadoRepartidor.DISPONIBLE) {
            System.out.println("No se puede asignar: El repartidor " + r.getNombre() + " no está disponible.");
            return false;
        }
        
        this.mensajero = r;
        this.status = EstadoPedido.EN_REPARTO;
        this.mensajero.asignarPedido(this);
        return true;
    }

    public boolean finalizarEntrega() {
        if (this.status != EstadoPedido.EN_REPARTO || this.mensajero == null) {
            System.out.println("Error: El pedido no se encuentra en reparto actualmente.");
            return false;
        }
        
        this.status = EstadoPedido.ENTREGADO;
        this.mensajero.finalizarPedido(this);
        return true;
    }

    public EstadoPedido getStatus() { return status; }
    public double getImporteTotal() { return importeTotal; }
    public String getIdentificador() { return identificador; }
}