package cards;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import random.Random;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    public ArrayList<String> getCard() {
        Properties photos = new Properties();
        Properties cards = new Properties();

        try {
            File fileCards = new File("src\\main\\resources\\NameOfCards.properties");
            cards.load(new FileReader(fileCards));

            File filePhoto = new File("src\\main\\resources\\NameOfPhotoCards.properties");
            photos.load(new FileReader(filePhoto));

            Random i = new Random();
            int number = i.randomNumber();
            String nameOfPhoto = photos.getProperty("prediction" + number);
            String nameOfCard = cards.getProperty("prediction" + number);

            Document document = Jsoup.connect("https://alma-taro.ru/taro-day/"
                            + nameOfCard + "-znachenie-karta-dnya/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements card = document.select("section.main > div h2");
            Elements mining = document.select("div.col-md-8.col-md-push-4 p");
            String cardName = card.get(0).text();
            String predicton = cardName.substring(0, cardName.length() - 32) + "\n\n" + mining.text();
            return new ArrayList<>(Arrays.asList(predicton, nameOfPhoto));
        } catch (IOException e) {
            System.err.println(e);
        }
        return new ArrayList<>();
    }
}
