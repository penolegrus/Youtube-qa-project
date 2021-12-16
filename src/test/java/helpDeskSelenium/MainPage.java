package helpDeskSelenium;

import core.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {

    @FindBy(xpath = "//select[@name='queue']")
    private WebElement queueList;

    @FindBy(xpath = "//select[@name='queue']//option[@value='2']")
    private WebElement queueChooseValue;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement titleInput;

    @FindBy(xpath = "//textarea[@name='body']")
    private WebElement descriptionInput;

    @FindBy(xpath = "//select[@name='priority']")
    private WebElement priorityList;

    @FindBy(xpath = "//option[@value='3']")
    private WebElement priorityChooseValue;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(id = "userDropdown")
    private WebElement loginButton;

    public MainPage(){
        PageFactory.initElements(driver, this);
    }

    public MainPage submitTicket(String title, String description, String email){
        queueList.click();
        queueChooseValue.click();
        titleInput.sendKeys(title);
        descriptionInput.sendKeys(description);
        priorityList.click();
        priorityChooseValue.click();
        emailInput.sendKeys(email, Keys.ENTER);
        return this;
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }
}
