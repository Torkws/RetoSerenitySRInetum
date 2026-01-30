package inetum.tasks;

import inetum.interactions.ClickInteraction;
import inetum.interactions.InputInteraction;
import inetum.interactions.WaitInteraction;
import inetum.ui.CheckoutPage;
import inetum.ui.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConfirmacionCompraTask implements Task {


    public static Performable sending() {
        return instrumented(ConfirmacionCompraTask.class);
    }

    @Override
    @Step("{0} se realiza la confirmación de la compra: ")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitInteraction.forElementToBeClickable(CheckoutPage.FINISH_BUTTON),
                ClickInteraction.on(CheckoutPage.FINISH_BUTTON, "botón Confimar compra")
        );
    }
}
