package inetum.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Credentials {

    private static final Map<String, String> CREDENTIALS = new HashMap<>();

    static {
        loadCredentialsFromProperties();
    }

    private static void loadCredentialsFromProperties() {
        Properties props = new Properties();
        try (InputStream input = Credentials.class.getClassLoader()
                .getResourceAsStream("credentials.properties")) {
            if (input == null) {
                System.err.println("Archivo credentials.properties no encontrado");
                return;
            }
            props.load(input);

            // Cargar todas las propiedades que terminen con .password
            for (String key : props.stringPropertyNames()) {
                if (key.endsWith(".password")) {
                    String username = key.substring(0, key.length() - 9); // Remover ".password"
                    String password = props.getProperty(key);
                    CREDENTIALS.put(username, password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer credentials.properties: " + e.getMessage());
        }
    }


    public static String getPassword(String username) {
        return CREDENTIALS.get(username);
    }

}
