package gestionPedidos;

import java.util.Objects;

public class Producto {
    private String denominacion;
    private double precioUnitario;
    private Categoria categoria;

    public Producto(String denominacion, double precioUnitario, Categoria categoria) {
        this.denominacion = denominacion;
        this.precioUnitario = precioUnitario;
        this.categoria = categoria;
    }

    public String getDenominacion() { return denominacion; }
    public double getPrecioUnitario() { return precioUnitario; }
    public Categoria getCategoria() { return categoria; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(denominacion, producto.denominacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denominacion);
    }
}