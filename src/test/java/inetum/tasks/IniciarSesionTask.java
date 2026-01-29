package inetum.tasks;

import inetum.ui.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class IniciarSesionTask implements Task {
    private final String username;
    private final String password;

    public IniciarSesionTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Performable sending(String username, String password) {
        return instrumented(IniciarSesionTask.class, username, password);
    }

    @Override
    @Step("{0} inicia sesión correctamente: ")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
                Click.on(LoginPage.LOGIN_BUTTON)
        );
    }
}
