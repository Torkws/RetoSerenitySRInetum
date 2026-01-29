package inetum.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {
    public static final Target USERNAME_FIELD = Target.the("campo de nombre de usuario")
            .located(By.id("user-name"));

    public static final Target PASSWORD_FIELD = Target.the("campo de contraseña")
            .located(By.id("password"));

    public static final Target LOGIN_BUTTON = Target.the("botón de inicio de sesión")
            .located(By.id("login-button"));

    public static final Target LOGIN_LOGO = Target.the("logo de Sauce Demo")
            .located(By.cssSelector("div.login_logo"));
}
