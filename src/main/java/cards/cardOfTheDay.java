package cards;

import random.random;

import java.io.*;
import java.util.Properties;

/**
 * Класс Карта дня
 */
public class cardOfTheDay implements cards {
    /**
     * Функция получения одной из Карт Дня
     *
     * @return возвращает описание одной из карт
     */
    @Override
    public String sayCards() {
        Properties property = new Properties();

        try {
            File file = new File("C:\\Users\\79091\\Downloads\\tgBot-main\\" +
                    "tgBot-main\\src\\main\\resources\\ar_properties.properties");
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