package api.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType("application/json")
                .build();
    }

    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
    public static ResponseSpecification responseSpecError400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }
    public static ResponseSpecification responseSpec(int status){
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }
    public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installSpecification(RequestSpecification requestSpec){
        RestAssured.requestSpecification =requestSpec;
    }
    public static void installSpecification(ResponseSpecification responseSpec){
        RestAssured.responseSpecification =responseSpec;
    }


}
