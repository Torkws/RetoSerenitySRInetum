package inetum.utils;

import com.github.javafaker.Faker;

public class CommonUtils {

    public static String GenerarDataMock(String fieldName) {
        Faker faker = new Faker();
        switch (fieldName.toLowerCase()) {
            case "firstname":
                return faker.name().firstName();
            case "lastname":
                return faker.name().lastName();
            case "postalcode":
                return faker.address().zipCode();
            default:
                throw new IllegalArgumentException("Field name not recognized: " + fieldName);
        }
    }
}
