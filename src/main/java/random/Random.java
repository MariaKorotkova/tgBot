package random;

import java.util.Collections;
import java.util.List;

/**
 * Класс рандом
 */
public class random implements randomFunction {
    /**
     * Функция расчёта вероятности
     *
     * @return возвращает вероятность наступления события
     */

    public String randomFunc() {
        int i = (int) (1 + Math.random() * 99);
        return "Вероятность наступления события: " + i;
    }

    /**
     * Функция получения рандомного числа
     *
     * @return возвращает рандомное число от 1 до 22
     */

    public int randomNumber() {
        int j = (int) (1 + Math.random() * 21);
        return j;
    }
}
