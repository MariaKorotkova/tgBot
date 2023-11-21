package cards;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Класс Карты Судьбы
 */
public class CardOfTheDestiny implements CardDestiny {
    /**
     * Метод получения номера
     *
     * @param date дата рождения
     * @return возвращает номер, посчитанный по дате рождения
     */
    public int numberOfTheDestiny(String date) {
        ArrayList<String> dateOfBirth = new ArrayList<>(Arrays.asList(date.split("")));
        if (date.length() == 8) {
            int sum = 0;
            for (String s : dateOfBirth) sum = sum + Integer.parseInt(s);
            if (sum > 22) {
                int anotherSum = 0;
                while (sum != 0) {
                    anotherSum += sum % 10;
                    sum = sum / 10;
                }
                sum = anotherSum;
            }
            return sum;
        } else {
            return 0;
        }
    }

    /**
     * Метод получения описания одной из Карт Судьбы
     *
     * @param num номер, посчитанный по дате рождения
     * @return возвращает карту, соответствующую номеру
     */
    public ArrayList<String> getCardsOfTheDestinyDescription(int num) {
        Properties photos = new Properties();
        Properties cards = new Properties();

        try {
            File filePhotos = new File(Thread.currentThread().getContextClassLoader()
                    .getResource("NameOfCardsPhoto.properties").toURI());
            photos.load(new FileReader(filePhotos));

            File fileCards = new File(Thread.currentThread().getContextClassLoader()
                    .getResource("NameOfCards.properties").toURI());
            cards.load(new FileReader(fileCards));

            String nameOfPhoto = photos.getProperty("prediction" + num);
            String nameOfCard = cards.getProperty("prediction" + num);

            Document document = Jsoup.connect("https://alma-taro.ru/znachenie-taro/"
                    + nameOfCard + "-znachenie/").get();
            Elements card = document.select("section.main > div h2");
            Elements mining = document.select("div.col-md-8.col-md-push-4 p");
            String cardName = card.get(0).text();
            String prediction = cardName.substring(0, cardName.length() - 40) + "\n\n" + mining.get(0).text();
            return new ArrayList<>(Arrays.asList(prediction, nameOfPhoto));
        } catch (IOException | URISyntaxException e) {
            System.err.println(e);
        }
        return new ArrayList<>();
    }
}
