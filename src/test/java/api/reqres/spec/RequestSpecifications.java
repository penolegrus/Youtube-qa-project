package api.spec;

import api.reqres.registration.SuccessUserReg;
import api.reqres.users.UserData;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestSpecifications {

    public static <T> List<T> getRequestList(String endPoint, String jsonPath, Class<T> extractedClass){
        return given()
                .when()
                .get(endPoint)
                .then()
                .log().all()
                .extract().body().jsonPath().getList(jsonPath, extractedClass);
    }

    public static <T> T postRequest(PostRequest body, String endPoint, Class<T> extractedClass){
        return given()
                .body(body)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .extract().as(extractedClass);
    }

    public static <T> T getRequest(String endPoint, Class<T> extractedClass){
        return given()
                .when()
                .get(endPoint)
                .then()
                .log().all()
                .extract().body().as(extractedClass);
    }

}
