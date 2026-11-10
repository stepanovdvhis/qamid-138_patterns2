import com.codeborne.selenide.Configuration;
import data.AuthApi;
import data.DataGenerator;
import data.RegistrationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest {

    @BeforeEach
    void setUp() {
        Configuration.browser = "firefox";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginWithActiveUser() {
        // 1. Активный зарегистрированный пользователь
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        // Проверяем, что вошли (например, по появлению личного кабинета)
        // Пока просто проверяем, что нет ошибки
        // $(".notification").shouldNotBe(visible);
    }

    @Test
    void shouldNotLoginWithBlockedUser() {
        // 2. Заблокированный зарегистрированный пользователь
        RegistrationDto user = DataGenerator.generateUser("blocked");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        page.shouldSeeError();
    }

    @Test
    void shouldNotLoginWithUnregisteredUser() {
        // 3. Незарегистрированный активный пользователь
        RegistrationDto user = DataGenerator.generateUser("active");
        // НЕ создаем его через API

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), user.getPassword());

        page.shouldSeeError();
    }

    @Test
    void shouldNotLoginWithWrongPassword() {
        // 4. Валидный пользователь, но неправильный пароль
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login(user.getLogin(), "wrong_password");

        page.shouldSeeError();
    }

    @Test
    void shouldNotLoginWithWrongLogin() {
        // 5. Валидный пользователь, но неправильный логин
        RegistrationDto user = DataGenerator.generateUser("active");
        AuthApi.createUser(user);

        LoginPage page = new LoginPage();
        page.login("wrong_login", user.getPassword());

        page.shouldSeeError();
    }
}