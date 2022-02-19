package sms;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class SmsApi {
    private final String URL = "https://smshub.org/stubs/handler_api.php";
    private final String API = "14734U2abe38923c16452d782c105a93595b19";

    public String getAccountBalance(){
        String body = given().contentType(ContentType.HTML)
                .queryParam("api_key", API)
                .queryParam("action", "getBalance")
                .get(URL).then().log().body()
                .extract().body().htmlPath().getString("body");
        String[] data = body.split(":");
        return data[1];
    }

    public PhoneData getPhone(){
        String body = given().contentType(ContentType.HTML)
                .queryParam("api_key", API)
                .queryParam("action", "getNumber")
                .queryParam("service", "pm")
                .queryParam("country", "0")
                .get(URL).then().log().body()
                .extract().body().htmlPath().getString("body");
        String[] data = body.split(":");
        PhoneData phoneData = new PhoneData(data[1],data[2]);
        return phoneData;
    }
}
