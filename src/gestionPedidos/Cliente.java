package gestionPedidos;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Pedido> misPedidos;

    public Cliente(String nombre, String email, String telefono) {
        super(nombre, email, telefono);
        this.misPedidos = new ArrayList<>();
    }

    public void registrarPedido(Pedido p) {
        misPedidos.add(p);
    }
}
