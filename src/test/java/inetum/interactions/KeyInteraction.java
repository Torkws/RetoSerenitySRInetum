package inetum.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Clase de Interactions para manejar el envío de teclas como Escape.
 * Las Interactions son acciones atómicas y reutilizables del patrón Screenplay.
 */
public class KeyInteraction implements Interaction {

    private final CharSequence key;
    private final Target target;
    private final String description;

    public KeyInteraction(CharSequence key, Target target, String description) {
        this.key = key;
        this.target = target;
        this.description = description;
    }


    public static KeyInteraction pressEscape() {
        return instrumented(KeyInteraction.class, Keys.ESCAPE, null, "Escape");
    }


    public static KeyInteraction pressEscape(Target target) {
        return instrumented(KeyInteraction.class, Keys.ESCAPE, target, "Escape");
    }


    @Override
    @Step("{0} presiona la tecla #description")
    public <T extends Actor> void performAs(T actor) {
        if (target != null) {
            actor.attemptsTo(
                    SendKeys.of(key).into(target)
            );
        } else {
            WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();
            driver.switchTo().activeElement().sendKeys(key);
        }
    }
}




