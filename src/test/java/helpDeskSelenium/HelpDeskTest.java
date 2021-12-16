package helpDeskSelenium;

import core.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void createTicketTest() {
        /*
        String title = "Тестовый тайтл тикета"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String description = "описание тествое";
        String email = "azazel@mail.ru";
        driver.get(ConfigurationProvider.SITE_URL);
        CreatedTicketPage createdTicket = new MainPage().submitTicket(title, description, email)
                .openLoginPage()
                .auth(ConfigurationProvider.USER_LOGIN, ConfigurationProvider.USER_PASSWORD)
                .search(title);
        Assert.assertTrue(createdTicket.getSummaryTitle().contains(title));
        Assert.assertEquals(description, createdTicket.getIssueField());
        Assert.assertEquals(email, createdTicket.getEmailField());

         */
    }
}
