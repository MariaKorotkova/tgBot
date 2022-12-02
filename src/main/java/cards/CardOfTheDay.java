package cards;

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
    public String sayCards() {
        Properties property = new Properties();

        try {
            File file = new File("src\\main\\resources\\CardsOfTheDay.properties");
            property.load(new FileReader(file));

            Random i = new Random();
            int number = i.randomNumber();
            return property.getProperty("prediction" + number);
        } catch (IOException e) {
            System.err.println("Ошибка!");
        }
        return "";
    }
}