package cards;

import java.io.*;

/**
 * Класс Карты Судьбы
 */
public class cardOfTheDestiny implements anotherCards {
    /**
     * Функция получения номера
     * @param date дата рождения
     * @return возвращает номер, расчитанный по дате рождения
     */
    public int numberOfTheDestiny(String date) {
        //сделать проверку на правильность ввода
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
     * @param num номер, расчитанный по дате рождения
     * @return возвращает карту, соответствующую номеру
     */
    public StringBuilder cardsOfTheDestiny(int num) {
        try (FileReader reader = new FileReader("destiny.txt")) {
            StringBuilder sb = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                char ch = (char) character;
                sb.append(ch);
            }
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < sb.length() - 2; j++) {
                boolean prov1 = Character.isDigit(sb.charAt(j));
                boolean prov2 = Character.isDigit(sb.charAt(j + 1));
                if (prov1 && prov2 && (Integer.parseInt(sb.substring(j, j + 1)) ==
                        (num / 10)) && (Integer.parseInt(sb.substring(j + 1, j + 2)) ==
                        (num % 10))) {
                    for (int p = j + 2; p < sb.length(); p++) {
                        if (sb.charAt(p) == '*') break;
                        result.append(sb.charAt(p));
                    }
                }
            }
            return result;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new StringBuilder();
    }
}
