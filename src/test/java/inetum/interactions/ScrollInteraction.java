package inetum.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Clase de Interactions para manejar scrolls en el sistema.
 * Las Interactions son acciones atómicas y reutilizables del patrón Screenplay.
 *
 * Proporciona diferentes tipos de scroll:
 * - Scroll a un elemento específico
 * - Scroll hacia arriba
 * - Scroll hacia abajo
 * - Scroll personalizado con posiciones específicas
 */
public class ScrollInteraction implements Interaction {

    // Constantes para scroll
    private static final int SCROLL_AMOUNT = 500;  // píxeles a desplazar
    private static final String SCROLL_UP = "up";
    private static final String SCROLL_DOWN = "down";

    private final int pixelsToScroll;
    private final Target target;
    private final String direction;

    public ScrollInteraction(int pixelsToScroll, Target target, String direction) {
        this.pixelsToScroll = pixelsToScroll;
        this.target = target;
        this.direction = direction;
    }

    public static ScrollInteraction down(int pixels) {
        return instrumented(ScrollInteraction.class, pixels, null, SCROLL_DOWN);
    }

    public static ScrollInteraction down() {
        return instrumented(ScrollInteraction.class, SCROLL_AMOUNT, null, SCROLL_DOWN);
    }

    public static ScrollInteraction up(int pixels) {
        return instrumented(ScrollInteraction.class, pixels, null, SCROLL_UP);
    }

    public static ScrollInteraction up() {
        return instrumented(ScrollInteraction.class, SCROLL_AMOUNT, null, SCROLL_UP);
    }

    public static ScrollInteraction to(Target target) {
        return instrumented(ScrollInteraction.class, 0, target, "to");
    }


    public static ScrollInteraction toTop() {
        return instrumented(ScrollInteraction.class, 0, null, "top");
    }


    public static ScrollInteraction toBottom() {
        return instrumented(ScrollInteraction.class, 0, null, "bottom");
    }

    @Override
    @Step("{0} realiza un scroll")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = actor.abilityTo(BrowseTheWeb.class).getDriver();

        switch (direction) {
            case SCROLL_DOWN:
                scrollDown(driver, pixelsToScroll);
                break;
            case SCROLL_UP:
                scrollUp(driver, pixelsToScroll);
                break;
            case "to":
                if (target != null) {
                    scrollToElement(driver, actor);
                }
                break;
            case "top":
                scrollToTop(driver);
                break;
            case "bottom":
                scrollToBottom(driver);
                break;
        }
    }

    private void scrollDown(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ")");
    }

    private void scrollUp(WebDriver driver, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -" + pixels + ")");
    }

    private void scrollToTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }


    private void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    private void scrollToElement(WebDriver driver, Actor actor) {
        WebElement element = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
