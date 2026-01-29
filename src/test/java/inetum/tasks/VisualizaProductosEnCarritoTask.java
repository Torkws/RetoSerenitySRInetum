package inetum.tasks;

import inetum.interactions.ClickInteraction;
import inetum.interactions.WaitInteraction;
import inetum.models.CarritoInfo;
import inetum.ui.CommonPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class VisualizaProductosEnCarritoTask implements Task {
    private static List<CarritoInfo.ProductoCarrito> productosEnCarrito;

    public static Performable verificarProductos() {
        return instrumented(VisualizaProductosEnCarritoTask.class);
    }

    @Override
    @Step("{0} visualiza los productos en el carrito de compras")
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitInteraction.forElementToBeClickable(CommonPage.CART_ICON),
                ClickInteraction.on(CommonPage.CART_ICON)
        );

    }

}
