import com.codeborne.selenide.Configuration;
import data.AuthApi;
import data.DataGenerator;
import data.RegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginTest {

    @BeforeEach
    void setUp() {
        Configuration.browser = "firefox";
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithActiveUser() {
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        // Проверяем, что вошли (ошибки нет)
        $("[data-test-id='error-notification']").shouldNotBe(visible);
    }

    @Test
    void shouldNotLoginWithBlockedUser() {
        RegistrationDto user = DataGenerator.generateUser("blocked");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        page.shouldSeeError("Пользователь заблокирован");
    }

    @Test
    void shouldNotLoginWithUnregisteredUser() {
        RegistrationDto user = DataGenerator.generateUser("active");

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        page.shouldSeeError("Неверно указан логин или пароль");
    }

    @Test
    void shouldNotLoginWithWrongPassword() {
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), "wrong_password");

        page.shouldSeeError("Неверно указан логин или пароль");
    }

    @Test
    void shouldNotLoginWithWrongLogin() {
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login("wrong_login", user.getPassword());

        page.shouldSeeError("Неверно указан логин или пароль");
    }
}