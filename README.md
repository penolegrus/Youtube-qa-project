# Youtube-qa-project
Проект по автотестам для моего ютуб канала

# Тест кейс от подписчика №1, пакет appleInsider

1. Открыть сайт appleinsider.ru
2. Нажать на кнопку поиска сверху справа
3. Ввести текст "Чем iPhone 13 отличается от iPhone 12" и нажать Enter
4. В найденных статьях получить href атрибут первой найденной статьи
5. Убедиться, что href ссылка содержит слово Iphone 13 (обрати внимание как это слово выглядит в href ссылке)

# Тест кейс от подписчика №2, пакет hh
1) Открыть резюме, которое доступно только по ссылке: https://hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073
2) Получить информацию о профиле, используя HashMap или Class, в которой должны быть следующие атрибуты:
String sex, int age, String city, boolean confirmedPhoneNumber, boolean readyToRelocate;
3) Убедится, что ожидаемый результат и актуальный равны

Ожидаемый результат sex = "М", age = 25, city = "Санкт-Петербург", confirmedPhoneNubmer = true, readyToRelocate = false; 
