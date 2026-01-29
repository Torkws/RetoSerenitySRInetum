package inetum.stepdefinitions;

import inetum.questions.CommonQuestions;
import inetum.tasks.IniciarSesionTask;
import inetum.ui.CommonPage;
import inetum.ui.InventoryPage;
import inetum.utils.Credentials;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class LoginStepDefinitions {

    @When("^el usuario inicia sesión con el usuario (.*)$")
    public void el_usuario_inicia_sesión_con_el_usuario(String user) {
        String password = Credentials.getPassword(user);
        OnStage.theActorInTheSpotlight().attemptsTo(
                IniciarSesionTask.sending(user, password)
        );
    }

    @When("^el usuario inicia sesión con usuario (.*) y contraseña (.*)")
    public void el_usuario_inicia_sesión_con_el_usuario_y_contraseña(String user, String pass) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                IniciarSesionTask.sending(user, pass)
        );
    }

    @Then("el usuario debería ser redirigido a la página de productos")
    public void el_usuario_debería_ser_redirigido_a_la_página_de_productos() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(CommonQuestions.checkIfObjectIsDisplayed(
                        InventoryPage.INVENTORY_PAGE_TITLE,
                        "El usuario fue redirigido a la página de productos - Validando título"
                ))
                        .isTrue()
        );
    }

    @Then("el inventario de productos debería ser visible")
    public void el_inventario_de_productos_debería_ser_visible() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(CommonQuestions.checkIfObjectIsDisplayed(
                        InventoryPage.PRODUCTS_INVENTORY,
                        "El inventario de productos es visible en la página"
                ))
                        .isTrue()
        );
    }

    @Then("^se debería mostrar un mensaje de error de login (.*)")
    public void se_debería_mostrar_un_mensaje_de_error_de_login(String messageError) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Ensure.that(CommonQuestions.getObjectText(
                        CommonPage.ERROR_MESSAGE_LOGIN_CSS,
                        "Validando que se muestra el mensaje de error esperado: " + messageError
                ))
                        .contains(messageError)
        );
    }


}