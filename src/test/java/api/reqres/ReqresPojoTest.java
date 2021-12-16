package api.reqres;

import api.reqres.colors.Data;
import api.reqres.registration.Register;
import api.reqres.registration.SuccessUserReg;
import api.reqres.registration.UnsuccessUserReg;
import api.reqres.users.UserData;
import api.reqres.users.UserTime;
import api.reqres.users.UserTimeResponse;
import api.spec.Specifications;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresPojoTest {
    private final static String URL = "https://reqres.in/";

    /**
     * 1. Получить список пользователей со второй страница на сайте https://reqres.in/
     * 2. Убедиться что id пользователей содержаться в их avatar;
     * 3. Убедиться, что email пользователей имеет окончание reqres.in;
     */
    @Test
    public void checkAvatarContainsIdTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        //1 способ сравнивать значения напрямую из экземпляров класса
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        //проверка аватар содержит айди
        users.forEach(x->Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        //проверка почты оканчиваются на reqres.in
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        //2 способ сравнивать значения через получения списков
        //список с аватарками
        List<String> realPeopleAvatars  = users.stream()
                .map(UserData::getAvatar)
                .collect(Collectors.toList());
        //список с айди
        List<String> realPeopleIds = users.stream()
                .map(x->x.getId().toString())
                .collect(Collectors.toList());
        //проверка через сравнение двух списков
        for (int i = 0; i<realPeopleAvatars.size(); i++){
            Assert.assertTrue(realPeopleAvatars.get(i).contains(realPeopleIds.get(i)));
        }
    }

    /**
     * 1. Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
     * 2. Тест для успешной регистрации
     */
    @Test
    public void successUserRegTest(){
        Integer UserId = 4;
        String UserPassword = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Register user = new Register("eve.holt@reqres.in","pistol");
        SuccessUserReg successUserReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then()
                .log().all()
                .extract().as(SuccessUserReg.class);
        Assert.assertNotNull(successUserReg.getId());
        Assert.assertNotNull(successUserReg.getToken());
        Assert.assertEquals(UserId, successUserReg.getId());
        Assert.assertEquals(UserPassword, successUserReg.getToken());
    }

    /**
     * 1. Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
     * 2. Тест для неуспешной регистрации (не введен пароль)
     */
    @Test
    public void unSuccessUserRegTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecError400());
        Register peopleSecond = new Register("sydney@fife","");
        UnsuccessUserReg unSuccessUserReg = given()
                .body(peopleSecond)
                .when()
                .post("/api/register")
                .then()  //.assertThat().statusCode(400) проверить статус ошибки, если не указана спецификация
                .log().body()
                .extract().as(UnsuccessUserReg.class);
        Assert.assertNotNull(unSuccessUserReg.getError());
        Assert.assertEquals("Missing password", unSuccessUserReg.getError());
    }

    /**
     * Используя сервис https://reqres.in/ убедиться, что операция LIST<RESOURCE> возвращает данные,
     * отсортированные по годам.
     */
    @Test
    public void checkSortedYearsTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<Data> data = given()
                .when()
                .get("/api/unknown")
                .then()
                .log().all()
                .extract().body().jsonPath().getList("data", Data.class);

        List<Integer> dataYears = data.stream().map(Data::getYear).collect(Collectors.toList());
        List<Integer> sortedDataYears = dataYears.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(dataYears, sortedDataYears);
        System.out.println(dataYears);
        System.out.println(sortedDataYears);
    }

    /**
     * Тест 4.1
     * Используя сервис https://reqres.in/ попробовать удалить второго пользователя и сравнить статус-код
     */
    @Test
    public void deleteUserTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpec(204));
        given().when().delete("/api/users/2")
                .then()
                .log().all();
    }

    /**
     * Используя сервис https://reqres.in/ обновить информацию о пользователе и сравнить дату обновления с текущей датой на машине
     */
    @Test
    public void checkServerAndPcDateTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserTime user = new UserTime("morpheus","zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("/api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        //так как время считается в плоть до миллисекунд, необходимо убрать последние 5 символов, чтобы время было одинаковое
        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");

        Assert.assertEquals(response.getUpdatedAt().replaceAll(regex,""),currentTime);
    }



}
