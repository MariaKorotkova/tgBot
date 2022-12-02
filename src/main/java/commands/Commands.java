package commands;

import cards.CardsDestiny;
import cards.CardsDay;
import cards.CardOfTheDestiny;
import cards.CardOfTheDay;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import random.Random;

import java.io.*;

import org.json.simple.JSONObject;

/**
 * Класс команд
 */
public class Commands {
    /**
     * Функция выбора команды
     *
     * @param name название команды
     */
    public String[] command(String name, String status) throws IOException, ParseException {

        switch (name) {
            case "/help":
                return new String[]{"""
                        /newUser:  Создание пользователя
                        /cardOfTheDay:  Карта дня - карта предсказание на день
                        /cardOfTheDestiny:  Карта судьбы по дате рождения
                        /horoscopeOfTheDay:  Гороскоп на день
                        /possibility:  Предсказание вероятности наступления события
                        """, " "};
            case "/cardOfTheDay":
                CardsDay j = new CardOfTheDay();
                return new String[]{j.sayCards(), " "};
            case "/cardOfTheDestiny":
                File file = new File("user.json");
                if (file.length() == 0) {
                    return new String[]{"Создайте пользователя: /newUser", " "};
                } else {
                    Object obj = new JSONParser().parse(new FileReader("date.json"));
                    JSONObject jo = (JSONObject) obj;
                    String date = (String) jo.get("Date");
                    CardsDestiny p = new CardOfTheDestiny();
                    return new String[]{p.cardsOfTheDestiny(p.numberOfTheDestiny(date)), " "};
                }
            case "/possibility":
                return new String[]{"Введите ваш вопрос:", "getPossibility"};
            case "/newUser":
                return new String[]{"Введите имя:", "getNameNewUser"};
            case "/horoscopeOfTheDay":
                return new String[]{"Введите знак зодиака", "getZodiac"};
            default:
                switch (status) {
                    case "getNameNewUser" -> {
                        JSONObject jsonName = new JSONObject();
                        try {
                            jsonName.put("Name", name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try (PrintWriter out = new PrintWriter(new FileWriter("user.json"))) {
                            out.write(jsonName.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return new String[]{"Введите дату рождения в формате 01011999:", "getDateNewUser"};
                    }
                    case "getDateNewUser" -> {
                        JSONObject jsonDate = new JSONObject();
                        try {
                            jsonDate.put("Date", name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try (PrintWriter out = new PrintWriter(new FileWriter("date.json"))) {
                            out.write(jsonDate.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Object obj = new JSONParser().parse(new FileReader("user.json"));
                        JSONObject jo = (JSONObject) obj;
                        String nameAnswer = (String) jo.get("Name");
                        return new String[]{String.format("Привет, %s!", nameAnswer), " "};
                    }
                    case "getPossibility" -> {
                        Random i = new Random();
                        return new String[]{i.randomFunc(), " "};
                    }
                    case "getZodiac" -> {
                        return new String[]{"Тута будет гороскоп", " "};
                    }
                }
                return new String[]{"Неправильный запрос!", " "};
        }
    }
}