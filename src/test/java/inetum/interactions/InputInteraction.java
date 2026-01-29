package inetum.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Clase de Interactions para manejar la entrada de datos en campos de formulario.
 * Las Interactions son acciones atómicas y reutilizables del patrón Screenplay.
 */
public class InputInteraction implements Interaction {

    private final String value;
    private final Target target;

    public InputInteraction(String value, Target target) {
        this.value = value;
        this.target = target;
    }

    /**
     * Método estático para crear la interaction de forma fluida
     * Ejemplo: actor.attemptsTo(InputInteraction.withValue("test").into(LoginPage.USERNAME_FIELD))
     */
    public static InputInteraction withValue(String value) {
        return new InputInteraction(value, null);
    }

    /**
     * Método para especificar el target donde se ingresará el valor
     */
    public InputInteraction into(Target target) {
        return instrumented(InputInteraction.class, this.value, target);
    }

    @Override
    @Step("{0} ingresa '#value' en el campo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(value).into(target)
        );
    }
}
