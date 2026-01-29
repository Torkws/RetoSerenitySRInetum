package inetum.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CommonPage {
     public static final Target ERROR_BUTTON = Target.the("botón para cerrar error")
             .located(By.cssSelector("button.error-button"));

     public static final Target ERROR_MESSAGE_CONTAINER = Target.the("contenedor de mensaje de error")
             .located(By.cssSelector("div.error-message-container"));

     public static final Target ERROR_MESSAGE = Target.the("mensaje de error")
             .located(By.xpath("//div[@class='error-message-container']/h3"));

    public static final Target ERROR_MESSAGE_LOGIN_CSS = Target.the("mensaje de error de login (CSS)")
            .located(By.cssSelector("div.error-message-container h3"));

    public static Target CART_ICON = Target.the("Icono del carrito de compras")
            .locatedBy("//a[@class='shopping_cart_link']");
}




