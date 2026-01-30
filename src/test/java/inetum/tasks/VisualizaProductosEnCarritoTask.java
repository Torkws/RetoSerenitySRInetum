package inetum.tasks;

import inetum.interactions.ClickInteraction;
import inetum.interactions.WaitInteraction;
import inetum.models.CarritoInfo;
import inetum.questions.CommonQuestions;
import inetum.ui.CommonPage;
import inetum.ui.InventoryPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class VisualizaProductosEnCarritoTask implements Task {

    private static final String PRODUCTOS_KEY = "productos";

    public static Performable verificarProductos() {
        return instrumented(VisualizaProductosEnCarritoTask.class);
    }

    @Override
    @Step("{0} visualiza los productos en el carrito de compras y valida que los productos guardados existan")
    public <T extends Actor> void performAs(T actor) {
        List<CarritoInfo.ProductoCarrito> productosGuardados = obtenerProductosGuardados(actor);

        if (productosGuardados == null || productosGuardados.isEmpty()) {
            return;
        }

        actor.attemptsTo(
                WaitInteraction.forElementToBeClickable(CommonPage.CART_ICON),
                ClickInteraction.on(CommonPage.CART_ICON),
                WaitInteraction.forElementToBeVisible(InventoryPage.PRODUCT_ITEMS)
        );

        validarProductosEnCarrito(actor, productosGuardados);
    }

    @SuppressWarnings("unchecked")
    private List<CarritoInfo.ProductoCarrito> obtenerProductosGuardados(Actor actor) {
        return (List<CarritoInfo.ProductoCarrito>) actor.recall(PRODUCTOS_KEY);
    }


    private void validarProductosEnCarrito(Actor actor, List<CarritoInfo.ProductoCarrito> productos) {
        productos.forEach(producto -> validarProducto(actor, producto));
    }

    private void validarProducto(Actor actor, CarritoInfo.ProductoCarrito producto) {
        String nombreProducto = producto.getNombre();

        // Validar existencia del producto
        actor.attemptsTo(
                Ensure.that(CommonQuestions.checkIfObjectIsDisplayed(
                        InventoryPage.getCartProduct(nombreProducto),
                        "El producto '" + nombreProducto + "' está visible en el carrito"
                ))
                        .isTrue()
        );

        // Validar precio del producto
        String precioTexto = actor.asksFor(
                CommonQuestions.getObjectText(
                        InventoryPage.getProductPrice(nombreProducto),
                        "Obtener precio del producto '" + nombreProducto + "' en el carrito"
                )
        );

        validarYCompararPrecio(actor, nombreProducto, producto.getPrecio(), precioTexto);
    }

    private void validarYCompararPrecio(Actor actor, String nombreProducto, double precioGuardado, String precioTexto) {
        try {
            double precioCarrito = convertirPrecio(precioTexto);
            validarPrecioProducto(actor, nombreProducto, precioGuardado, precioCarrito);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error al validar el precio del producto: " + nombreProducto, e);
        }
    }

    private double convertirPrecio(String precioTexto) {
        return Double.parseDouble(precioTexto.replace("$", "").trim());
    }

    @Step("Validar que el precio del producto '{nombreProducto}' sea correcto: Esperado $'{precioGuardado}' = Actual $'{precioCarrito}'")
    private <T extends Actor> void validarPrecioProducto(T actor, String nombreProducto, double precioGuardado, double precioCarrito) {
        actor.attemptsTo(
                Ensure.that(precioGuardado == precioCarrito)
                        .isTrue()
        );
    }


}


