package inetum.tasks;

import inetum.interactions.ClickInteraction;
import inetum.interactions.InputInteraction;
import inetum.interactions.WaitInteraction;
import inetum.ui.CheckoutPage;
import inetum.utils.CommonUtils;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class RealizaCheckoutTask implements Task {
    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public RealizaCheckoutTask(String firstName, String lastName, String postalCode) {
        this.firstName = firstName.equalsIgnoreCase("random") ? CommonUtils.GenerarDataMock("firstName") : firstName;
        this.lastName = lastName.equalsIgnoreCase("random") ? CommonUtils.GenerarDataMock("lastName") : lastName;
        this.postalCode = postalCode.equalsIgnoreCase("random") ? CommonUtils.GenerarDataMock("postalCode") : postalCode;
    }
    public static Performable sending(String firstName, String lastName, String postalCode) {
        return instrumented(RealizaCheckoutTask.class, firstName, lastName, postalCode);
    }
    @Override
    @Step("{0} realiza el checkout con los datos: #firstName, #lastName, #postalCode")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitInteraction.forElementToBeClickable(CheckoutPage.CHECKOUT_BUTTON),
                ClickInteraction.on(CheckoutPage.CHECKOUT_BUTTON),

                WaitInteraction.forElementToBePresent(CheckoutPage.FIRSTNAME_FIELD),
                InputInteraction.withValue(firstName).into(CheckoutPage.FIRSTNAME_FIELD),

                WaitInteraction.forElementToBePresent(CheckoutPage.LASTNAME_FIELD),
                InputInteraction.withValue(lastName).into(CheckoutPage.LASTNAME_FIELD),

                WaitInteraction.forElementToBePresent(CheckoutPage.ZIP_FIELD),
                InputInteraction.withValue(postalCode).into(CheckoutPage.ZIP_FIELD),

                WaitInteraction.forElementToBeClickable(CheckoutPage.CONTINUE_BUTTON),
                Click.on(CheckoutPage.CONTINUE_BUTTON)
        );

    }
}