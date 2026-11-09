package data;

import com.github.javafaker.Faker;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static RegistrationDto generateUser(String status) {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        return new RegistrationDto(login, password, status);
    }
}