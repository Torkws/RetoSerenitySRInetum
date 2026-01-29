package inetum.stepdefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


public class loginStepdefinitions {

    @When("^el usuario inicia sesión con el usuario (.*) y contraseña (.*)")
    public void el_usuario_inicia_sesión_con_el_usuario_standard_user_y_contraseña_secret_sauce(String user, String pass) {
    }

    @Then("el usuario debería ser redirigido a la página de productos")
    public void el_usuario_debería_ser_redirigido_a_la_página_de_productos() {
    }

    @Then("el inventario de productos debería ser visible")
    public void el_inventario_de_productos_debería_ser_visible() {
    }

    @Then("^se debería mostrar un mensaje de error de login (.*)")
    public void se_debería_mostrar_un_mensaje_de_error_de_login(String messageError) {
    }


}