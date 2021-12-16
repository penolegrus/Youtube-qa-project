package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseSeleniumTest {
    protected WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        BaseSeleniumPage.setDriver(driver);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
