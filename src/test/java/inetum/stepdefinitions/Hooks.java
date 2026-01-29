package inetum.stepdefinitions;

import inetum.utils.CommonVariables;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import io.cucumber.java.Before;


public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Before
    public void setupBaseUrl() {
        // Obtener variables de ambiente desde Serenity
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.currentEnvironmentVariables();

        // Obtener el environment configurado
        String environment = environmentVariables.getProperty("environment");

        // Obtiene la URL resuelta según la configuración de environments en serenity.conf
        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("webdriver.base.url");
        CommonVariables.setUrlSauceDemo(baseUrl);

        // Configurar headless mode dinámicamente
        String headlessMode = environmentVariables.getProperty("headless.mode", "true");
        configureHeadlessMode(headlessMode, environmentVariables);

        if (environment == null || environment.isEmpty()) {
            System.out.println("Advertencia: No se definió 'environment'. Usando environment por defecto desde serenity.conf");
        } else {
            System.out.println("Environment seleccionado: " + environment);
        }

        if (baseUrl != null && !baseUrl.isEmpty()) {
            System.out.println("Base URL utilizada: " + baseUrl);
        } else {
            System.out.println("Advertencia: No se configuró Base URL para el environment: " + environment);
        }
    }

    private void configureHeadlessMode(String headlessMode, EnvironmentVariables environmentVariables) {
        String driver = environmentVariables.getProperty("webdriver.driver", "chrome");
        boolean isHeadless = Boolean.parseBoolean(headlessMode);

        if (isHeadless) {
            System.out.println("Modo headless: ACTIVADO (sin ventana visible)");
        } else {
            System.out.println("Modo headless: DESACTIVADO (navegador visible)");
            // Los argumentos se sobrescriben dinámicamente desde las capacidades
            System.setProperty("chrome.switches", "--start-maximized");
            System.setProperty("edge.switches", "--start-maximized");
        }
    }

}
