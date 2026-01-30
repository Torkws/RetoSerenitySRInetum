package inetum.stepdefinitions;

import inetum.models.CarritoInfo;
import inetum.questions.CommonQuestions;
import inetum.tasks.*;
import inetum.ui.CheckoutPage;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

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
        OnStage.theActorInTheSpotlight().attemptsTo(
                RealizaCheckoutTask.sending(firstName, lastName, postalCode)
        );

    }

    @And("se procesa la confirmación de la compra")
    public void seProcesaLaConfirmacionDeLaCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConfirmacionCompraTask.sending()
        );
    }

    @And("debería visualizar la confirmación de la compra")
    public void deberiaVisualizarLaConfirmacionDeLaCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ValidarConfirmacionCompraTask.validar()
        );
    }

    @And("^debería ver el mensaje de compra exitosa (.*)$")
    public void deberiaVerElMensaje(String mensajeEsperado) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El mensaje de error es correcto:",
                        CommonQuestions.getObjectText(CheckoutPage.CONFIRMATION_MESSAGE), equalTo(mensajeEsperado))
        );
    }



}
