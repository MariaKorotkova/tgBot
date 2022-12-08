package cards;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import random.Random;

import java.io.*;
import java.util.Properties;

/**
 * Класс Карта дня
 */
public class CardOfTheDay implements CardsDay {
    /**
     * Функция получения одной из Карт Дня
     *
     * @return возвращает описание одной из карт
     */
    @Override
    public String[] sayCards() {
        Properties property = new Properties();

        try {
            File file = new File("src\\main\\resources\\NameOfCards.properties");
            property.load(new FileReader(file));

            Random i = new Random();
            int number = i.randomNumber();
            String name = property.getProperty("prediction" + number);

            Document document = Jsoup.connect("https://alma-taro.ru/taro-day/"
                            + name + "-znachenie-karta-dnya/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements card = document.select("section.main > div h2");
            Elements mining = document.select("div.col-md-8.col-md-push-4 p");
            String cardName = card.get(0).text();
            String predicton = cardName.substring(0, cardName.length() - 32) + "\n\n" + mining.text();
            System.out.println(number);
            return new String[]{predicton, name};
        } catch (IOException e) {
            System.err.println("Ошибка!");
        }
        return new String[]{};
    }
}
