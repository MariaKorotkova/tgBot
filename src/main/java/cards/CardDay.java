package cards;

import java.util.ArrayList;

/**
 * Интерфейс Карта дня
 */
public interface CardDay {
    /**
     * Метод получения описания одной из Карт Дня
     */
    ArrayList<String> getCardOfTheDayDescription();
}