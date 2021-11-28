package api.reqres;

import api.reqres.spec.Specifications;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresNoPojoTest {
    private final static String URL = "https://reqres.in";

    /**
     * 1. Получить список пользователей со второй страница на сайте https://reqres.in/
     * 2. Убедиться что id пользователей содержаться в их avatar;
     * 3. Убедиться, что email пользователей имеет окончание reqres.in;
     */
    @Test
    public void checkAvatarContainsIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Response response = given()
                .when()
                .get("/api/users?page=2")
                .then()
                .log().all()
                .body("page", equalTo(2))
                .body("data.id.", notNullValue())
                .body("data.email.", notNullValue())
                .body("data.first_name.", notNullValue())
                .body("data.last_name.", notNullValue())
                .body("data.avatar.", notNullValue())
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();

        List<String> avatars = jsonResponse.get("data.avatar");
        List<Integer> ids = jsonResponse.get("data.id");

        for (int i = 0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }

        List<String> emails = jsonResponse.get("data.email");
        Assert.assertTrue(emails.stream().allMatch(x->x.contains("@reqres.in")));
    }

    /**
     * 1. Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
     * 2. Тест для успешной регистрации
     */
    @Test
    public void successUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Map<String, String> data = new HashMap<>();
        data.put("email", "eve.holt@reqres.in");
        data.put("password", "pistol");
        Response response = given()
                .body(data)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();

        Integer id = jsonResponse.get("id");
        Assert.assertEquals(4, (int) id);

        String token = jsonResponse.get("token");
        Assert.assertEquals(token,"QpwL5tke4Pnpja7X4");
    }

    /**
     * 1. Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
     * 2. Тест для неуспешной регистрации (не введен пароль)
     */
    @Test
    public void unSuccessUserRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Map<String, String> data = new HashMap<>();
        data.put("email", "sydney@file");
        Response response = given()
                .body(data)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        String error = jsonResponse.get("error");
        Assert.assertEquals(error, "Missing password");
    }

    /**
     * Используя сервис https://reqres.in/ убедиться, что операция LIST<RESOURCE> возвращает данные,
     * отсортированные по годам.
     */
    @Test
    public void checkSortedYearsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Response response = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().all()
                .body("data.id.", notNullValue())
                .body("data.name.", notNullValue())
                .body("data.year", notNullValue())
                .body("data.color", notNullValue())
                .body("data.pantone_value", notNullValue())
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        List<String> years = jsonResponse.getList("data.year");
        System.out.println(years);
        Assert.assertTrue(years.stream().sorted().collect(Collectors.toList()).equals(years));
    }
}
