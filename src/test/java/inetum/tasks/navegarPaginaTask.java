package inetum.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class navegarPaginaTask implements Task {
    private final String url;

    public navegarPaginaTask(String url) {
        this.url = url;
    }
    @Override
    @Step("{0} Navega a la página: #url")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }

    public static Performable sending(String url) {
        return instrumented(navegarPaginaTask.class, url);
    }
}
