package inetum.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {

    public static Target CHECKOUT_BUTTON = Target.the("Botón de Checkout")
            .locatedBy("//button[@id='checkout']");
    public static Target FIRSTNAME_FIELD = Target.the("")
            .locatedBy("//input[@id='first-name']");
    public static Target LASTNAME_FIELD = Target.the("")
            .locatedBy("//input[@id='last-name']");
    public static Target ZIP_FIELD = Target.the("")
            .locatedBy("//input[@id='postal-code']");
    public static Target CONTINUE_BUTTON = Target.the("")
            .locatedBy("//input[@id='continue']");

    public static Target TITLE_CHECKOUT_OVERVIEW = Target.the("Título de la página de resumen de checkout")
            .locatedBy("//span[@data-test='title']");
    public static Target FINISH_BUTTON = Target.the("Botón de finalizar orden")
            .locatedBy("//button[@id='finish']");
    public static Target ERROR_MESSAGE_CHECKOUT = Target.the("Mensaje de error en checkout")
            .locatedBy("//div[contains(@class, 'error-message-container') and contains(@class, 'error')]//h3[@data-test='error']");



    public static Target BACK_BUTTON = Target.the("Botón de volver a productos")
            .locatedBy("//button[@id='back-to-products' and @data-test='back-to-products']");

    public static Target TITLE_ORDER_COMPLETE = Target.the("Título de orden completada")
            .locatedBy("//span[@data-test='title' and contains(@class, 'title')]");

    public static Target CONFIRMATION_MESSAGE = Target.the("Mensaje de confirmación de la compra")
            .locatedBy("//h2[@data-test='complete-header' and contains(@class, 'complete-header')]");
}
