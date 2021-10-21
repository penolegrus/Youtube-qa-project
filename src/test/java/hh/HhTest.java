package hh;

import core.BaseTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HhTest extends BaseTest {
    /**
     * Ссылка на резюме для получения данных
     */
    private final static String URL = "https://hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073";

    /**
     * Константы для обозначения ключей для хэш карты
     */
    public static String GENDER;
    public static String CITY;
    public static String AGE;
    public static String RELOCATE;
    public static String CONFIRMED_PHONE;

    /**
     * Получает атрибуты кандидата через HashMap, сравнивает актуальный и ожидаемый результат
     */
    @Test
    public void checkAttributesMap(){
        HhResumePage hhResumePage = new HhResumePage(URL);
        //создаем карту ключ-значение с ожидаемыми данными
        Map<String,Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(GENDER,"М");
        expectedAttributes.put(AGE, 25);
        expectedAttributes.put(CITY, "Санкт-Петербург");
        expectedAttributes.put(RELOCATE, false);
        expectedAttributes.put(CONFIRMED_PHONE, true);

        //получаем карту ключ-значение с актуальными данными
        Map<String,Object> actualAttributes = hhResumePage.getAttributes();

        //сравниваем ожидаемый и актуальный результат
        Assert.assertEquals(expectedAttributes,actualAttributes);
    }

    /**
     * Получает атрибуты кандидата через Class, сравнивает актуальный и ожидаемый результат двумя способами
     */
    @Test
    public void checkAttributesClass(){
        HhResumePage hhResumePage = new HhResumePage(URL);
        //создаем экземпляр класса с ожидаемыми данными через конструктор
        Resume expectedResume = new Resume("М", "Санкт-Петербург",25, true,false);

        //получаем экземпляр класса с актуальными данными через конструктор
        Resume actualResume = new Resume(hhResumePage.getGenderEasy(), hhResumePage.getCityEasy(), hhResumePage.getAge(),
                hhResumePage.isPhoneConfirmed(),hhResumePage.isReadyToRelocate());

        //1 способ сравнивнения классов
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedResume,actualResume));

        //2 способ сравнения отдельных переменных в классе
        Assert.assertEquals(expectedResume.getGender(), actualResume.getGender());
        Assert.assertEquals(expectedResume.getCity(), actualResume.getCity());
        Assert.assertEquals(expectedResume.getAge(), actualResume.getAge());
        Assert.assertEquals(expectedResume.isNumberConfirmed(), actualResume.isNumberConfirmed());
        Assert.assertEquals(expectedResume.isReadyToRelocate(), actualResume.isReadyToRelocate());
    }
}
