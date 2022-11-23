package cards;

import java.io.*;
import java.util.Properties;

/**
 * Класс Карты Судьбы
 */
public class CardOfTheDestiny implements AnotherCards {
    /**
     * Функция получения номера
     *
     * @param date дата рождения
     * @return возвращает номер, расчитанный по дате рождения
     */
    public int numberOfTheDestiny(String date) {
        String[] dateOfBirth = date.split("");
        if (date.length() == 8) {
            int sum = 0;
            for (String s : dateOfBirth) sum = sum + Integer.parseInt(s);
            if (sum <= 22) {
                return sum;
            } else {
                int anotherSum = 0;
                while (sum != 0) {
                    anotherSum += sum % 10;
                    sum = sum / 10;
                }
                sum = anotherSum;
                return sum;
            }
        } else {
            return 0;
        }
    }

    /**
     * Функция получения одной из Карт Судьбы
     *
     * @param num номер, расчитанный по дате рождения
     * @return возвращает карту, соответствующую номеру
     */
    public String cardsOfTheDestiny(int num) {
        Properties property = new Properties();

        try {
            File file = new File("src\\main\\resources\\app.properties");
            property.load(new FileReader(file));

            String prediction = property.getProperty("prediction" + Integer.toString(num));
            return prediction;
        } catch (IOException e) {
            System.err.println("Ошибка!");
        }
        return new String();
    }
}
