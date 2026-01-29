package inetum.actors;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class definitionActors {

    public static Actor actor;

    public static Actor definirActor(String name) {
        Actor actor = theActorCalled(name);
        actor.can(BrowseTheWeb.with(ThucydidesWebDriverSupport.getDriver()));
        return actor;
    }

}
