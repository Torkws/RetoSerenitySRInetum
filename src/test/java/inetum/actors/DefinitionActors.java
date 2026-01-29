package inetum.actors;

import inetum.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class DefinitionActors {

    public static Actor actor;

    public static Actor definirActor(String name) {
        Actor actor = theActorCalled(name);
        actor.can(BrowseTheWeb.with(ThucydidesWebDriverSupport.getDriver()));
        return actor;
    }

}
