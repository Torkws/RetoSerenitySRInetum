package inetum.tasks;

import inetum.interactions.WaitInteraction;
import inetum.questions.CommonQuestions;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

import static inetum.ui.CheckoutPage.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.Matchers.equalTo;

public class ValidarConfirmacionCompraTask implements Task {

    public static Performable validar() {
        return instrumented(ValidarConfirmacionCompraTask.class);
    }

    @Override
    @Step("{0} valida la confirmación de la compra")
    public <T extends Actor> void performAs(T actor) {
        validarTituloOrdenCompletada(actor);
        validarMensajeConfirmacion(actor);
        validarBotonVolverAProductos(actor);
    }

    @Step("Validar que el título de orden completada está visible con el texto '{TITLE_ORDER_COMPLETE_EXPECTED}'")
    private <T extends Actor> void validarTituloOrdenCompletada(T actor) {
        actor.attemptsTo(
                WaitInteraction.forElementToBeVisible(TITLE_ORDER_COMPLETE)
        );
        actor.attemptsTo(
                Ensure.that(CommonQuestions.checkIfObjectIsDisplayed(
                        TITLE_ORDER_COMPLETE,
                        "El título de orden completada está visible"
                ))
                        .isTrue(),
                Ensure.that(CommonQuestions.getObjectText(TITLE_ORDER_COMPLETE,
                        "El texto del título es 'Checkout: Complete!'"))
                        .isEqualTo(TITLE_ORDER_COMPLETE_EXPECTED)
        );
    }

    @Step("Validar que el mensaje de confirmación de la compra está visible")
    private <T extends Actor> void validarMensajeConfirmacion(T actor) {
        actor.attemptsTo(
                Ensure.that(CommonQuestions.checkIfObjectIsDisplayed(
                        CONFIRMATION_MESSAGE,
                        "El mensaje de confirmación está visible"
                ))
                        .isTrue()
        );
    }

    @Step("Validar que el botón de volver a productos está visible")
    private <T extends Actor> void validarBotonVolverAProductos(T actor) {
        actor.attemptsTo(
                Ensure.that(CommonQuestions.checkIfObjectIsDisplayed(
                        BACK_BUTTON,
                        "El botón de volver a productos está visible"
                ))
                        .isTrue()
        );
    }
}