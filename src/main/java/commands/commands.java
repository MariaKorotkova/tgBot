package commands;

import cards.anotherCards;
import cards.cards;
import cards.cardOfTheDestiny;
import cards.cardOfTheDay;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import random.random;

import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONObject;

/** Класс команд */
public class commands implements commandsInt {
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
                        /cardOfTheDay: Карта дня - карта предсказание из старших арканов
                        /cardOfTheDestiny: Карта судьбы рассчитывается по дате рождения
                        /possibility: Предсказание вероятности наступления события
                        """);
                break;
            //или считать информацию из файла
            case "/cardOfTheDay":
                cards j = new cardOfTheDay();
                System.out.println("Ваша карта дня:");
                System.out.println(j.sayCards());
                break;
            case "/cardOfTheDestiny":
                //System.out.println("Введите дату рождения в формате 01011999:");
                //Scanner in = new Scanner(System.in);
                //String date = in.nextLine();
                Object obj = new JSONParser().parse(new FileReader("user.json"));
                JSONObject jo = (JSONObject) obj;
                String date = (String) jo.get("Date");
                System.out.println("date: " + date);
                anotherCards p = new cardOfTheDestiny();
                System.out.println(p.cardsOfTheDestiny(p.numberOfTheDestiny(date)));
                break;
            case "/possibility":
                System.out.println("Введите ваш вопрос:");
                //добавить блок try?
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