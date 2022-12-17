package commands;

import cards.CardsDestiny;
import cards.CardsDay;
import cards.CardOfTheDestiny;
import cards.CardOfTheDay;
import horoscope.MusicForZodiac;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import random.Random;
import horoscope.HoroscopeOfTheDay;
import horoscope.Compatibility;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<String> command(String name, String status) throws IOException, ParseException {

        switch (name) {
            case "/start":
                return new ArrayList<>(Arrays.asList("""
                        Привет, я GadalkaBot!
                        Включи клавиатуру, чтобы было удобней общаться /keyboard
                        Нажми /help , чтобы посмотреть,что я могу делать
                        """, " "));
            case "/help":
                return new ArrayList<>(Arrays.asList("""
                        /new_user:  Создание пользователя
                        /keyboard:  Создание клавиатуры
                        
                        Карты Таро:
                        /card_of_the_day:  Карта дня - карта предсказание на день
                        /card_of_the_destiny:  Карта судьбы по дате рождения
                        /possibility:  Предсказание вероятности наступления события
                        
                        Гороскоп:
                        /horoscope_of_the_day:  Гороскоп на день
                        /compatibility:  Совместимость знаков зодиака
                        /music:  Музыка для знаков зодиака
                        /chart:  График дня для знака зодиака
                        
                        """, " "));
            case "/card_of_the_day":
                CardsDay cardDay = new CardOfTheDay();
                ArrayList<String> answer = cardDay.getCard();
                return new ArrayList<>(Arrays.asList(answer.get(0), " ", answer.get(1)));
            case "/card_of_the_destiny":
                File fileUser = new File("user.json");
                if (fileUser.length() == 0) {
                    return new ArrayList<>(Arrays.asList("Создайте пользователя: /new_user", " "));
                } else {
                    Object obj = new JSONParser().parse(new FileReader("date.json"));
                    JSONObject jo = (JSONObject) obj;
                    String date = (String) jo.get("Date");
                    CardsDestiny cardDestiny = new CardOfTheDestiny();
                    ArrayList<String> result = cardDestiny.cardsOfTheDestiny(cardDestiny.numberOfTheDestiny(date));
                    return new ArrayList<>(Arrays.asList(result.get(0), " ", result.get(1)));
                }
            case "/possibility":
                return new ArrayList<>(Arrays.asList("Введите ваш вопрос:", "getPossibility"));
            case "/compatibility":
                return new ArrayList<>(Arrays.asList("Введите Ваши знаки зодиака и пол в формате " +
                        "\nОвен ж Стрелец м", "getSigns"));
            case "/new_user":
                return new ArrayList<>(Arrays.asList("Введите имя:", "getNameNewUser"));
            case "/horoscope_of_the_day":
                return new ArrayList<>(Arrays.asList("Введите знак зодиака", "getZodiac"));
            case "/music":
                return new ArrayList<>(Arrays.asList("Введите знак зодиака", "getZodiacForMusic"));
            case "/chart":
                return new ArrayList<>(Arrays.asList("Ведите знак зодиака", "getZodiacForChart"));
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
                        return new ArrayList<>(Arrays.asList("Введите дату рождения в формате 01011999:", "getDateNewUser"));
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
                        return new ArrayList<>(Arrays.asList(String.format("Привет, %s!", nameAnswer), " "));
                    }
                    case "getPossibility" -> {
                        Random i = new Random();
                        return new ArrayList<>(Arrays.asList(i.randomFunc(), " "));
                    }
                    case "getZodiac" -> {
                        HoroscopeOfTheDay i = new HoroscopeOfTheDay();
                        return new ArrayList<>(Arrays.asList(i.horoscope(name), " "));
                    }
                    case "getZodiacForMusic" -> {
                        MusicForZodiac i = new MusicForZodiac();
                        return new ArrayList<>(Arrays.asList(i.getMusic(name), " ", "SendAudio"));
                    }
                    case "getSigns" -> {
                        Compatibility i = new Compatibility();
                        return new ArrayList<>(Arrays.asList(i.getCompatibilityOfSigns(name), " "));
                    }
                    case "getZodiacForChart" -> {
                        return new ArrayList<>(Arrays.asList("график", " "));
                    }
                }
                return new ArrayList<>(Arrays.asList("Неправильный запрос!", " "));
        }
    }
}
