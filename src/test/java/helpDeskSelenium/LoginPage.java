package helpDeskSelenium;

import core.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseSeleniumPage {
    @FindBy(id="username")
    private WebElement userInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public TicketsPage auth(String user, String password) {
        userInput.sendKeys(user);
        passwordInput.sendKeys(password, Keys.ENTER);
        return new TicketsPage();
    }
}
