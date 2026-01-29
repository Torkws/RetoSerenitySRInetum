package inetum.stepdefinitions;

import inetum.actors.definitionActors;
import inetum.tasks.navegarPaginaTask;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;
import inetum.utils.CommonVariables; // Importamos el wrapper para manejar URL_SAUCE_DEMO

import static inetum.actors.definitionActors.*;


public class commonWebStepDefinitions {

    @Given("que el (.*) navega a la página de inicio de sesión de Sauce Demo$")
    public void navegarAPaginaDeInicioDeSesion(String actor) {

        definitionActors.actor = definirActor(actor);

        OnStage.theActorCalled(actor).attemptsTo(
                navegarPaginaTask.sending(CommonVariables.getUrlSauceDemo())
        );

    }

}
