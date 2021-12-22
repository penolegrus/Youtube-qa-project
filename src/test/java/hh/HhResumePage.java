package hh;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница с Резюме на сайте hh
 */
public class HhResumePage {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
    private final SelenideElement liveString = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement confirmedNumber = $x("//div[@data-qa='resume-contacts-phone']//span[1]");
    private final SelenideElement city = $x("//span[@data-qa='resume-personal-address']");

    /**
     * Константы для обозначения ключей для хэш карты
     */
    public static String GENDER = "Пол";
    public static String CITY = "Город";
    public static String AGE = "Возраст";
    public static String RELOCATE = "Готовность к переезду";
    public static String CONFIRMED_PHONE = "Подтрвежденный номер телефона";

    public HhResumePage(String url) {
        Selenide.open(url);
    }

    /**
     * Возвращает карту (пары ключ-значение) с актуальными данными о резюме
     * Ключ карты является атрибут с типом String, значением карты является атрибут с типом Object (любым типом)
     *
     * Заинлайненный вариант получения хэш карты с значениями. Упрощенный вариант, можно увидеть в тестовом классе
     * @return заполненная карта с резюме
     */
    public Map<String,Object> getAttributes(){
        return new HashMap<String ,Object>(){{
            put(GENDER, getGenderHard());
            put(CITY, getCityHard());
            put(AGE, getAge());
            put(RELOCATE, isReadyToRelocate());
            put(CONFIRMED_PHONE, isPhoneConfirmed());
        }};
    }

    /**
     * Проверяет подтвержден ли телефон в профиле или нет
     * @return видимость галочки
     */
    public boolean isPhoneConfirmed(){
        return confirmedNumber.isDisplayed();
    }

    /**
     * Возвращает город, в котором живет кандидат
     * @return город кандидата
     */
    public String getCityEasy(){
        return city.getText();
    }

    /**
     * Разбивает строку об информации с проживанием кандидата в массив на части с разделением ", "
     *
     * Строка о проживании, которая будет получена: Санкт-Петербург, не готов к переезду, не готов к командировкам
     *
     * Массив с разбитыми частями будет иметь следующие части
     * [0] - Санкт-Петербург, [1] - не готов к переезду, [2] - не готов к командировкам
     *
     * @return город, в котором живет кандидат из общей строки с информацией о проживании
     */
    public String getCityHard(){
        return liveString.getText().split(", ")[0];
    }

    /**
     * Проверяет готов ли кандидат к переезду или нет
     * @return готовновсть к переезду
     */
    public boolean isReadyToRelocate(){
        return !liveString.getText().split(", ")[1].equals("не готов к переезду");
    }

    /**
     * Получает возраст кандидата из резюме через использование регулярных выражений
     *
     * Регулярное выражение "[^0-9]" говорит о том, что нужно исключить все, что не начинается с цифры
     * Можно реализовать через регулярное выражение "\\D+" тем самым исключить все символы из строки
     *
     * @return возраст кандидата в числовом формате
     */
    public int getAge(){
        return Integer.parseInt(age.getText().replaceAll("[^0-9]",""));
    }

    /**
     * Возвращает пол кандидата с помощью логического условия
     * equals можно заменить на ==
     * @return М или Ж, в зависимости от пола
     */
    public String getGenderEasy(){
        String genderValue = gender.getText();
        if(genderValue.equals("Мужчина")){
            return "М";
        }
        return "Ж";
    }

    /**
     * Возвращает пол кандидата через тернарный оператор
     * @return М или Ж, в зависимости от пола
     */
    public String getGenderHard(){
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }

}
