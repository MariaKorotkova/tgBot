package random;

/**
 * Класс рандом
 */
public class Random implements RandomFunction {
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
        return (int) (1 + Math.random() * 21);
    }
}
