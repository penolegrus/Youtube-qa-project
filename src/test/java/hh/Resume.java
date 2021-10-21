package hh;

/**
 * Класс для описания атрибутов кандидата из страницы с резюме на hh
 */
public class Resume {
    /**
     * Атрибуты для кандидата
     */
    private final String gender;
    private final String city;
    private final int age;
    private final boolean isNumberConfirmed;
    private final boolean isReadyToRelocate;


    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public boolean isNumberConfirmed() {
        return isNumberConfirmed;
    }

    public boolean isReadyToRelocate() {
        return isReadyToRelocate;
    }

    public String getCity() {
        return city;
    }

    /**
     * Конструктор класса
     * @param gender пол кандидата
     * @param city город кандидата
     * @param age возраст кандидата
     * @param isNumberConfirmed является ли номер подтвержденным
     * @param isReadyToRelocate готов ли кандидат к переезду
     */
    public Resume(String gender, String city, int age, boolean isNumberConfirmed, boolean isReadyToRelocate) {
        this.gender = gender;
        this.city = city;
        this.age = age;
        this.isNumberConfirmed = isNumberConfirmed;
        this.isReadyToRelocate = isReadyToRelocate;
    }
}
