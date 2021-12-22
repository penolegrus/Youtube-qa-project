package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;

/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest {

    /**
     * Инициализация selenide с настройками
     */
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @Before
    public void init(){
        setUp();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
