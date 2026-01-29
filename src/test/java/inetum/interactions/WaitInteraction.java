package inetum.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;


/**
 * Clase de Interactions para manejar esperas en el sistema.
 * Las Interactions son acciones atómicas y reutilizables del patrón Screenplay.
 *
 * Utiliza WebDriverWait en lugar de Thread.sleep() para esperas inteligentes
 * que terminan cuando se cumplen las condiciones esperadas.
 */
public class WaitInteraction implements Interaction {

    // Enumeración para definir los tipos de espera
    public enum WaitCondition {
        VISIBLE("visible"),
        CLICKABLE("clickeable"),
        PRESENT("presente");

        private final String description;

        WaitCondition(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Constante para el timeout por defecto en segundos
    private static final long DEFAULT_TIMEOUT = 10L;

    private final long seconds;
    private final Target target;
    private final WaitCondition condition;

    public WaitInteraction(long seconds, Target target, WaitCondition condition) {
        this.seconds = seconds;
        this.target = target;
        this.condition = condition;
    }


    public static WaitInteraction forElementToBeVisible(Target target) {
        return instrumented(WaitInteraction.class, DEFAULT_TIMEOUT, target, WaitCondition.VISIBLE);
    }

    public static WaitInteraction forElementToBeVisible(Target target, long seconds) {
        return instrumented(WaitInteraction.class, seconds, target, WaitCondition.VISIBLE);
    }

    public static WaitInteraction forElementToBeClickable(Target target) {
        return instrumented(WaitInteraction.class, DEFAULT_TIMEOUT, target, WaitCondition.CLICKABLE);
    }

    public static WaitInteraction forElementToBeClickable(Target target, long seconds) {
        return instrumented(WaitInteraction.class, seconds, target, WaitCondition.CLICKABLE);
    }

    public static WaitInteraction forElementToBePresent(Target target) {
        return instrumented(WaitInteraction.class, DEFAULT_TIMEOUT, target, WaitCondition.PRESENT);
    }

    public static WaitInteraction forElementToBePresent(Target target, long seconds) {
        return instrumented(WaitInteraction.class, seconds, target, WaitCondition.PRESENT);
    }

    @Override
    @Step("{0} espera hasta que el elemento esté #condition.description (máximo #seconds segundos)")
    public <T extends Actor> void performAs(T actor) {
        if (target != null) {
            switch (condition) {
                case VISIBLE:
                    actor.attemptsTo(
                            WaitUntil.the(target, isVisible()).forNoMoreThan(seconds).seconds()
                    );
                    break;
                case CLICKABLE:
                    actor.attemptsTo(
                            WaitUntil.the(target, isClickable()).forNoMoreThan(seconds).seconds()
                    );
                    break;
                case PRESENT:
                    actor.attemptsTo(
                            WaitUntil.the(target, isPresent()).forNoMoreThan(seconds).seconds()
                    );
                    break;
            }
        }
    }
}
