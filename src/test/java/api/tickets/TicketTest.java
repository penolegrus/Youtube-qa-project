package api.tickets;

import api.reqres.registration.UnsuccessUserReg;
import api.spec.Specifications;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static io.restassured.RestAssured.given;

public class TicketTest {
    private final String URL = "https://10.130.0.31:25232/api/";
    @Test
    public void ticketPost(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateNow = sdf.format(new Date());
        Specifications.installSpecification(Specifications.requestSpec(URL));
        TicketPojo ticket = new TicketPojo("2021-12-30", "Ura",
                "Test ticket for Ura", dateNow, dateNow,
                "ura@mail.ru", 1, true,"Test title description", "test resolution",
                1,"test secretkey", 0,0,0);
        TicketResponsePojo responsePojo = given()
                .body(ticket)
                .when()
                .post("api/tickets/")
                .then()
                .log().body()
                .extract().as(TicketResponsePojo.class);
        Assert.assertNotNull(responsePojo.getId());
    }
}
