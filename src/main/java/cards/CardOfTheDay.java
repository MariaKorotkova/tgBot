package cards;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import random.Random;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Класс Карта дня
 */
public class CardOfTheDay implements CardDay {
    /**
     * Метод получения одной из Карт Дня
     *
     * @return возвращает описание одной из карт
     */
    @Override
    public ArrayList<String> getCardOfTheDayDescription() {
        Properties photos = new Properties();
        Properties cards = new Properties();

        try {
            File filePhoto = new File(Thread.currentThread().getContextClassLoader()
                    .getResource("NameOfCardsPhoto.properties").toURI());
            photos.load(new FileReader(filePhoto));

            File fileCards = new File(Thread.currentThread().getContextClassLoader()
                    .getResource("NameOfCards.properties").toURI());
            cards.load(new FileReader(fileCards));

            Random i = new Random();
            int number = i.randomNumber();
            String nameOfPhoto = photos.getProperty("prediction" + number);
            String nameOfCard = cards.getProperty("prediction" + number);

            Document document = Jsoup.connect("https://alma-taro.ru/taro-day/"
                    + nameOfCard + "-znachenie-karta-dnya/").get();
            Elements card = document.select("section.main > div h2");
            Elements mining = document.select("div.col-md-8.col-md-push-4 p");
            String cardName = card.get(0).text();
            String predicton = cardName.substring(0, cardName.length() - 32) + "\n\n" + mining.text();
            return new ArrayList<>(Arrays.asList(predicton, nameOfPhoto));
        } catch (IOException | URISyntaxException e) {
            System.err.println(e);
        }
        return new ArrayList<>();
    }
}
