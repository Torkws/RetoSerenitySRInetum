package inetum.models;

import java.util.List;
import java.util.Objects;

/**
 * Modelo para almacenar información del carrito de compras
 * Contiene la lista de productos seleccionados y el total esperado
 */
public class CarritoInfo {
    private final List<ProductoCarrito> productosSeleccionados;
    private final double totalEsperado;

    public CarritoInfo(List<ProductoCarrito> productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
        this.totalEsperado = calcularTotal(productosSeleccionados);
    }

    private double calcularTotal(List<ProductoCarrito> productos) {
        if (productos == null || productos.isEmpty()) {
            return 0.0;
        }
        return productos.stream()
                .mapToDouble(ProductoCarrito::getPrecio)
                .sum();
    }

    public List<ProductoCarrito> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public double getTotalEsperado() {
        return totalEsperado;
    }

    @Override
    public String toString() {
        return "CarritoInfo{" +
                "productosSeleccionados=" + productosSeleccionados +
                ", totalEsperado=" + totalEsperado +
                '}';
    }


    public static class ProductoCarrito {
        private final String nombre;
        private final double precio;

        public ProductoCarrito(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public double getPrecio() {
            return precio;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProductoCarrito that = (ProductoCarrito) o;
            return Double.compare(that.precio, precio) == 0 &&
                    Objects.equals(nombre, that.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, precio);
        }

        @Override
        public String toString() {
            return nombre + " - $" + String.format("%.2f", precio);
        }
    }
}




