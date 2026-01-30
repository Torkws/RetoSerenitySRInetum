package inetum.tasks;

import inetum.interactions.WaitInteraction;
import inetum.models.CarritoInfo;
import inetum.ui.InventoryPage;
import inetum.ui.CommonPage;
import inetum.interactions.ClickInteraction;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AgregaProductosTask implements Task {
    private final List<CarritoInfo.ProductoCarrito> productos;

    public AgregaProductosTask(List<CarritoInfo.ProductoCarrito> productos) {
        this.productos = productos;
    }

    public static Performable adding(List<CarritoInfo.ProductoCarrito> productos) {
        return instrumented(AgregaProductosTask.class, productos);
    }

    @Override
    @Step("{0} agrega productos y se redirige al carrito")
    public <T extends Actor> void performAs(T actor) {
        if (productos == null || productos.isEmpty()) {
            System.out.println("⚠️ No hay productos para agregar al carrito");
            return;
        }

        // Extraer nombres de productos
        List<String> nombresProductos = productos.stream()
                .map(CarritoInfo.ProductoCarrito::getNombre)
                .collect(Collectors.toList());

        nombresProductos.forEach(nombreProducto -> {
            System.out.println("  ✓ Agregando: " + nombreProducto);
            actor.attemptsTo(
                    WaitInteraction.forElementToBeClickable(InventoryPage.getProductAddButton(nombreProducto)),
                    ClickInteraction.on(InventoryPage.getProductAddButton(nombreProducto))
            );
        });
        System.out.println("✓ Productos agregados exitosamente");


    }
}




