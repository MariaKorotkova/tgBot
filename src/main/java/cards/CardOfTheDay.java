package cards;

import random.random;

import java.io.*;
import java.util.Properties;

/**
 * Класс Карта дня
 */
public class CardOfTheDay implements Cards {
    /**
     * Функция получения одной из Карт Дня
     *
     * @return возвращает описание одной из карт
     */
    @Override
    public String sayCards() {
        Properties property = new Properties();

        try {
            File file = new File("src\\main\\resources\\app.properties");
            property.load(new FileReader(file));

            random i = new random();
            int number = i.randomNumber();
            String rawPrediction = property.getProperty("prediction" + Integer.toString(number));
            return rawPrediction;
        } catch (IOException e) {
            System.err.println("Ошибка!");
        }
        return new String();
    }
}