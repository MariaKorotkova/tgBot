package cards;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * Класс Карты Судьбы
 */
public class CardOfTheDestiny implements CardsDestiny {
    /**
     * Функция получения номера
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
     * Функция получения одной из Карт Судьбы
     *
     * @param num номер, посчитанный по дате рождения
     * @return возвращает карту, соответствующую номеру
     */
    public ArrayList<String> cardsOfTheDestiny(int num) {
        Properties photos = new Properties();
        Properties cards = new Properties();

        try {
            File filePhotos = new File("src\\main\\resources\\NameOfPhotoCards.properties");
            photos.load(new FileReader(filePhotos));

            File fileCards = new File("src\\main\\resources\\NameOfPhotoCards.properties");
            cards.load(new FileReader(fileCards));

            String nameOfPhoto = photos.getProperty("prediction" + num);
            String nameOfCard = cards.getProperty("prediction" + num);

            Document document = Jsoup.connect("https://alma-taro.ru/znachenie-taro/"
                            + nameOfCard + "-znachenie/")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements card = document.select("section.main > div h2");
            Elements mining = document.select("div.col-md-8.col-md-push-4 p");
            String cardName = card.get(0).text();
            String predicton = cardName.substring(0, cardName.length() - 40) + "\n\n" + mining.get(0).text();
            return new ArrayList<>(Arrays.asList(predicton, nameOfPhoto));
        } catch (IOException e) {
            System.err.println(e);
        }
        return new ArrayList<>();
    }
}
