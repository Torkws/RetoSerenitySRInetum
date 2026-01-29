package inetum.stepdefinitions;

import inetum.tasks.NavegarPaginaTask;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;
import inetum.utils.CommonVariables; // Importamos el wrapper para manejar URL_SAUCE_DEMO

import static inetum.actors.DefinitionActors.*;


public class CommonWebStepDefinitions {

    @Given("que el (.*) navega a la página de inicio de sesión de Sauce Demo$")
    public void navegarAPaginaDeInicioDeSesion(String actor) {

        DefinitionActors.actor = definirActor(actor);

        OnStage.theActorCalled(actor).attemptsTo(
                NavegarPaginaTask.sending(CommonVariables.getUrlSauceDemo())
        );

    }

}
