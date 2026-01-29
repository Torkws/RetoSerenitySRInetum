package inetum.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class NavigationInteraction implements Interaction {

    private final String url;

    public NavigationInteraction(String url) {
        this.url = url;
    }


    public static NavigationInteraction to(String url) {
        return instrumented(NavigationInteraction.class, url);
    }

    @Override
    @Step("{0} navega a la URL: #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url)
        );
    }
}
