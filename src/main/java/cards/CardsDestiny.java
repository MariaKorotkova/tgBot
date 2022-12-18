package cards;

import java.util.ArrayList;

/**
 * Интерфейс Карта судьбы
 */
public interface CardsDestiny {
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
    ArrayList<String> cardsOfTheDestiny(int num);
}
