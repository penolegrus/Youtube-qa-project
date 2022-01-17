package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.BaseSelenideTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;


public class WikiTest extends BaseSelenideTest {
    private final static String URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void openAllHrefs() {
        //открываем нужную страницу
        Selenide.open("https://ru.wikipedia.org/wiki/Java");
        //обозначаем необходимые нам элементы в которых содержится атрибут href
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        //создаем список, в который поместим ссылки
        List<String> links = new ArrayList<>();
        //1 способ заполнения списка значениями через цикл for each
        for (int i = 0; i < hrefs.size(); i++) {
            links.add(hrefs.get(i).getAttribute("href"));
        }
        //2 способ заполнения списка через цикл for
        for (SelenideElement href : hrefs) {
            links.add(href.getAttribute("href"));
        }
        //3 способ заполнения через stream api
        hrefs.forEach(x -> links.add(x.getAttribute("href")));

        //1 способ открытия ссылок через for each
        for (int i = 0; i < links.size(); i++) {
            Selenide.open(links.get(i));
        }
        //2 способ открытия всех ссылок через for
        for (String link : links) {
            Selenide.open(link);
        }
        //3 способ открытия всех ссылок через stream api
        links.forEach(Selenide::open);

        //как получить случайную ссылку
        //1 способ
        Selenide.open(links.get((int) (Math.random() * links.size() + 1)));

        //2 способ
        //создаем рандом
        Random random = new Random();
        for (int i = 0; i < links.size(); i++) {
            //получаем случайное число на основе размера списка
            int index = random.nextInt(links.size());
            //открываем случайную ссылку по индексу из списка
            Selenide.open(links.get(index));
        }
        //3 способ если нужно убирать ссылку после ее доставания из списка
        while (links.size() >0 ) {
            int index = random.nextInt(links.size());
            Selenide.open(links.get(index));
            links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
        }

        //пример работы стримапи
        List<Integer> hrefSizeEach = hrefs.stream().map(x->x.getAttribute("href").length()).collect(Collectors.toList());
    }
}
