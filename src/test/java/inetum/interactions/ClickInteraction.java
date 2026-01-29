package inetum.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClickInteraction implements Interaction {

    private final Target target;
    private final String description;

    public ClickInteraction(Target target, String description) {
        this.target = target;
        this.description = description;
    }

    public static ClickInteraction on(Target target) {
        return instrumented(ClickInteraction.class, target, "elemento");
    }

    public static ClickInteraction on(Target target, String description) {
        return instrumented(ClickInteraction.class, target, description);
    }

    @Override
    @Step("{0} hacer clic en #description")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(target)
        );
    }
}
