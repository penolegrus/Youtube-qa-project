package helpDeskSelenium;

import core.BaseSeleniumPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketsPage extends BaseSeleniumPage {
    @FindBy(id = "search_query")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@class='tickettitle']")
    private WebElement ticketBox;

    public TicketsPage(){
        PageFactory.initElements(driver, this);
    }

    public CreatedTicketPage search(String searchString){
        searchInput.click();
        searchInput.sendKeys(searchString, Keys.ENTER);
        ticketBox.click();
        return new CreatedTicketPage();
    }
}
