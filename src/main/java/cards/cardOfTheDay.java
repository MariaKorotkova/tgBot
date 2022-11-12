package cards;

import random.random;

import java.io.*;

/** Класс Карта дня */
public class cardOfTheDay implements cards {
    /**
     * Функция получения одной из Карт Дня
     * @return возвращает описание одной из карт
     */
    public StringBuilder sayCards() {
        try (FileReader reader = new FileReader("prediction.txt")) {
            random i = new random();
            int number = i.randomNumber();
            int character;
            StringBuilder sb = new StringBuilder();
            while ((character = reader.read()) != -1) {
                char ch = (char) character;
                sb.append(ch);
            }
            StringBuilder result = new StringBuilder();
            for (int j = 0; j < sb.length() - 2; j++) {
                Boolean prov1 = Character.isDigit(sb.charAt(j));
                Boolean prov2 = Character.isDigit(sb.charAt(j + 1));
                if (prov1 && prov2 && (Integer.parseInt(sb.substring(j, j + 1)) ==
                        (number / 10)) && (Integer.parseInt(sb.substring(j + 1, j + 2)) ==
                        (number % 10))) {
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