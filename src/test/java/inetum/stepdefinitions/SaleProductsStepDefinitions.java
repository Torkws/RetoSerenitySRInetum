package inetum.stepdefinitions;

import inetum.models.CarritoInfo;
import inetum.tasks.AgregaProductosTask;
import inetum.tasks.ObtieneProductosByFilter;
import inetum.tasks.VisualizaProductosEnCarritoTask;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

public class SaleProductsStepDefinitions {

    String firstName, lastName, postalCode;


    @And("^agrega (.*) productos con el filtro (.*) al carrito$")
    public void agregaProductosAlCarrito(String cantidadProductos, String filtro) {
        int cantidad = Integer.parseInt(cantidadProductos);

        OnStage.theActorInTheSpotlight().attemptsTo(
                ObtieneProductosByFilter.obtenerProductos(cantidad, filtro)
        );

        @SuppressWarnings("unchecked")
        var productos = (List<CarritoInfo.ProductoCarrito>) OnStage.theActorInTheSpotlight().recall(ObtieneProductosByFilter.PRODUCTOS_KEY);
        OnStage.theActorInTheSpotlight().attemptsTo(
                AgregaProductosTask.adding(productos)
        );
    }

    @And("se visualiza los productos en el carrito de compras")
    public void seVisualizaLosProductosEnElCarrito() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                VisualizaProductosEnCarritoTask.verificarProductos()
        );

    }

    @And("^se completa el proceso de llenado de formulario con los inputs (.*), (.*) y (.*)$")
    public void seCompletaElProcesoDeLlenadoDeFormulario(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;

    }

    @And("se procesa la confirmación de la compra")
    public void seProcesaLaConfirmacionDeLaCompra() {

    }

    @And("debería visualizar la confirmación de la compra")
    public void deberiaVisualizarLaConfirmacionDeLaCompra() {

    }

    @And("^debería ver el mensaje de compra exitosa (.*)$")
    public void deberiaVerElMensaje(String mensajeEsperado) {

    }



}
