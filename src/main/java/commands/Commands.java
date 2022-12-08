package commands;

import cards.CardsDestiny;
import cards.CardsDay;
import cards.CardOfTheDestiny;
import cards.CardOfTheDay;
import keyboard.MakeKeyboard;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import random.Random;
import horoscope.HoroscopeOfTheDay;

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
            case "/start":
                return new String[]{"""
                        Привет, я GadalkaBot!
                        Включи клавиатуру, чтобы было удобней общаться /keyboard
                        Нажми /help , чтобы посмотреть,что я могу делать
                        """, " "};
            case "/help":
                return new String[]{"""
                        /new_user:  Создание пользователя
                        /keyboard:  Создание клавиатуры
                        
                        Карты Таро:
                        /card_of_the_day:  Карта дня - карта предсказание на день
                        /card_of_the_destiny:  Карта судьбы по дате рождения
                        
                        Гороскоп:
                        /horoscope_of_the_day:  Гороскоп на день
                        
                        /possibility:  Предсказание вероятности наступления события
                        """, " "};
            case "/card_of_the_day":
                CardsDay j = new CardOfTheDay();
                String[] answer = j.sayCards();
                return new String[]{answer[0], " ", answer[1]};
            case "/card_of_the_destiny":
                File file1 = new File("user.json");
                if (file1.length() == 0) {
                    return new String[]{"Создайте пользователя: /new_user", " "};
                } else {
                    Object obj = new JSONParser().parse(new FileReader("date.json"));
                    JSONObject jo = (JSONObject) obj;
                    String date = (String) jo.get("Date");
                    CardsDestiny p = new CardOfTheDestiny();
                    return new String[]{p.cardsOfTheDestiny(p.numberOfTheDestiny(date)), " "};
                }
            case "/possibility":
                return new String[]{"Введите ваш вопрос:", "getPossibility"};
            case "/new_user":
                return new String[]{"Введите имя:", "getNameNewUser"};
            case "/horoscope_of_the_day":
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
                        HoroscopeOfTheDay i = new HoroscopeOfTheDay();
                        return new String[]{i.horoscope(name), " "};
                    }
                }
                return new String[]{"Неправильный запрос!", " "};
        }
    }
}