package cards;

import java.util.ArrayList;

/**
 * Интерфейс Карта судьбы
 */
public interface CardDestiny {
    /**
     * Метод получения номера
     *
     * @param date дата рождения
     */
    int numberOfTheDestiny(String date);

    /**
     * Метод получения одной из Карт Судьбы
     *
     * @param num номер, посчитанный по дате рождения
     */
    ArrayList<String> getCardsOfTheDestinyDescription(int num);
}
