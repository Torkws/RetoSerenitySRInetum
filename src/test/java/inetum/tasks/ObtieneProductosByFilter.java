package inetum.tasks;

import inetum.models.CarritoInfo;
import inetum.ui.InventoryPage;
import inetum.questions.CommonQuestions;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ObtieneProductosByFilter implements Task {
    private final int cantidad;
    private final String filtro;

    public static final String CARRITO_INFO_KEY = "carritoInfo";
    public static final String PRODUCTOS_KEY = "productos";

    public ObtieneProductosByFilter(int cantidad, String filtro) {
        this.cantidad = cantidad;
        this.filtro = filtro;
    }

    public static Performable obtenerProductos(int cantidad, String filtro) {
        return instrumented(ObtieneProductosByFilter.class, cantidad, filtro);
    }

    @Override
    @Step("{0} obtiene {cantidad} productos con filtro {filtro}")
    public <T extends Actor> void performAs(T actor) {
        // Obtener productos del inventario
        List<CarritoInfo.ProductoCarrito> productosCarrito = actor.asksFor(
                CommonQuestions.getProductsNameAndPrice(InventoryPage.PRODUCTS_INVENTORY)
        );

        // Aplicar filtro de ordenamiento
        switch (filtro.toUpperCase()) {
            case "PRECIOMAYOR":
                productosCarrito.sort(Comparator.comparingDouble(CarritoInfo.ProductoCarrito::getPrecio).reversed());
                break;
            case "PRECIOMENOR":
                productosCarrito.sort(Comparator.comparingDouble(CarritoInfo.ProductoCarrito::getPrecio));
                break;
            default:
        }

        // Seleccionar los primeros N productos
        List<CarritoInfo.ProductoCarrito> productosSeleccionados = productosCarrito.stream()
                .limit(cantidad)
                .collect(Collectors.toList());

        // Guardar información en el Actor
        CarritoInfo carritoInfo = new CarritoInfo(productosSeleccionados);
        actor.remember(CARRITO_INFO_KEY, carritoInfo);

        // Guardar lista de productos directamente (sin conversión)
        actor.remember(PRODUCTOS_KEY, productosSeleccionados);
    }
}



