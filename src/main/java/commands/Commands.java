package commands;

import cards.AnotherCards;
import cards.Cards;
import cards.CardOfTheDestiny;
import cards.CardOfTheDay;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import random.random;

import java.io.*;
import java.util.Scanner;

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
    public void command(String name) throws IOException, ParseException {
        switch (name) {
            case "/help":
                System.out.println("""
                        /newUser: Создание пользователя
                        /CardOfTheDay: Карта дня - карта предсказание из старших арканов
                        /CardOfTheDestiny: Карта судьбы рассчитывается по дате рождения
                        /possibility: Предсказание вероятности наступления события
                        """);
                break;
            case "/CardOfTheDay":
                Cards j = new CardOfTheDay();
                System.out.println("Ваша карта дня:");
                System.out.println(j.sayCards());
                break;
            case "/CardOfTheDestiny":
                File file = new File("user.json");
                if (file.length() == 0){
                    System.out.println("Создайте пользователя: /newUser");
                }
                else {
                    Object obj = new JSONParser().parse(new FileReader("user.json"));
                    JSONObject jo = (JSONObject) obj;
                    String date = (String) jo.get("Date");
                    System.out.println("date: " + date);
                    AnotherCards p = new CardOfTheDestiny();
                    System.out.println(p.cardsOfTheDestiny(p.numberOfTheDestiny(date)));
                }
                break;
            case "/possibility":
                System.out.println("Введите ваш вопрос:");
                Scanner question = new Scanner(System.in);
                String str = question.nextLine();
                random i = new random();
                System.out.println(i.randomFunc());
                break;
            case "/newUser":
                System.out.println("Введите имя:");
                Scanner scanner1 = new Scanner(System.in);
                String name_user = scanner1.nextLine();
                System.out.println("Введите дату рождения в формате 01011999:");
                Scanner scanner2 = new Scanner(System.in);
                String date1 = scanner2.nextLine();
                JSONObject json = new JSONObject();
                try {
                    json.put("Date", date1);
                    json.put("Name", name_user);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try (PrintWriter out = new PrintWriter(new FileWriter("user.json"))) {
                    out.write(json.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Неправильный запрос!");
                break;
        }
    }
}