package gestionPedidos;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== APP DE DELIVERY ===");

        // Registro de usuarios y sistema
        GestionEmpresa sistema = new GestionEmpresa();
        
        Cliente cliente = new Cliente("Ana Torres", "ana.t@mail.com", "654321987");
        Repartidor repartidor1 = new Repartidor("Luis Gómez", "luis.repartos@mail.com", "699887766", "Centro");
        
        sistema.registrarRepartidor(repartidor1);

        Producto pizza = new Producto("Pizza Barbacoa", 12.50, Categoria.COMIDA);
        Producto refresco = new Producto("Coca Cola Zero", 2.20, Categoria.BEBIDA);
        Producto helado = new Producto("Tarrina Vainilla", 4.00, Categoria.POSTRE);

        System.out.println("\n-> Creando pedidos de prueba...");
        Pedido miPedido1 = new Pedido("PED-001", cliente);
        miPedido1.agregarProducto(pizza);
        miPedido1.agregarProducto(refresco);

        Pedido miPedido2 = new Pedido("PED-002", cliente);
        miPedido2.agregarProducto(pizza);

        Pedido miPedido3 = new Pedido("PED-003", cliente);
        miPedido3.agregarProducto(helado);

        Pedido miPedido4 = new Pedido("PED-004", cliente);
        miPedido4.agregarProducto(refresco);

        boolean añadidoRepetido = miPedido1.agregarProducto(pizza);
        if (!añadidoRepetido) {
            System.out.println("Aviso: No se ha añadido 'Pizza Barbacoa' duplicada a PED-001.");
        }
        System.out.println("Importe total PED-001: " + miPedido1.getImporteTotal() + "€");

        // Pruebas de asignaciones automáticas y límites
        System.out.println("\n--- INICIO DE ASIGNACIONES AUTOMÁTICAS ---");
        
        System.out.println("\n-> Solicitando asignación para PED-001...");
        sistema.asignarRepartidorAutomatico(miPedido1);
        System.out.println("Pedidos asignados a " + repartidor1.getNombre() + ": " + repartidor1.getPedidosAsignados().size() + "/3");
        System.out.println("Estado de " + repartidor1.getNombre() + ": " + repartidor1.getEstadoActual());

        System.out.println("\n-> Solicitando asignación para PED-002...");
        sistema.asignarRepartidorAutomatico(miPedido2);
        System.out.println("Pedidos asignados a " + repartidor1.getNombre() + ": " + repartidor1.getPedidosAsignados().size() + "/3");
        System.out.println("Estado de " + repartidor1.getNombre() + ": " + repartidor1.getEstadoActual());

        System.out.println("\n-> Solicitando asignación para PED-003...");
        sistema.asignarRepartidorAutomatico(miPedido3);
        System.out.println("Pedidos asignados a " + repartidor1.getNombre() + ": " + repartidor1.getPedidosAsignados().size() + "/3");
        System.out.println("Estado de " + repartidor1.getNombre() + ": " + repartidor1.getEstadoActual());

        System.out.println("\n-> Intentando asignar PED-004 (con el repartidor ya saturado)...");
        sistema.asignarRepartidorAutomatico(miPedido4);
        System.out.println("Estado de PED-004: " + miPedido4.getStatus());

        System.out.println("\n--- FINALIZANDO ENTREGA DE PED-001 ---");
        System.out.println("Entregando pedido PED-001...");
        miPedido1.finalizarEntrega();
        System.out.println("Estado de PED-001 tras entrega: " + miPedido1.getStatus());
        System.out.println("Pedidos asignados a " + repartidor1.getNombre() + ": " + repartidor1.getPedidosAsignados().size() + "/3");
        System.out.println("Estado de " + repartidor1.getNombre() + " tras entrega: " + repartidor1.getEstadoActual()); 

        System.out.println("\n-> Solicitando asignación para PED-004 de nuevo...");
        sistema.asignarRepartidorAutomatico(miPedido4); 
        System.out.println("Pedidos asignados a " + repartidor1.getNombre() + ": " + repartidor1.getPedidosAsignados().size() + "/3");
        System.out.println("Estado de " + repartidor1.getNombre() + ": " + repartidor1.getEstadoActual());

        // Entregar los pedidos que quedan
        System.out.println("\n--- ENTREGANDO RESTO DE PEDIDOS (PED-002, PED-003, PED-004) ---");
        miPedido2.finalizarEntrega();
        miPedido3.finalizarEntrega();
        miPedido4.finalizarEntrega();
        
        System.out.println("Pedidos asignados finales a " + repartidor1.getNombre() + ": " + repartidor1.getPedidosAsignados().size() + "/3");
        System.out.println("Estado final de " + repartidor1.getNombre() + ": " + repartidor1.getEstadoActual());
    }
}