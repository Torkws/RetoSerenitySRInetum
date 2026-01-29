package inetum.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import inetum.interactions.NavigationInteraction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class NavegarPaginaTask implements Task {
    private final String url;

    public NavegarPaginaTask(String url) {
        this.url = url;
    }
    @Override
    @Step("{0} Navega a la página: #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                NavigationInteraction.to(url)
        );
    }

    public static Performable sending(String url) {
        return instrumented(NavegarPaginaTask.class, url);
    }
}
