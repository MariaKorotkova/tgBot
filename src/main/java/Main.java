import commands.Commands;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Основной класс
 */
class Main {
    /**
     * Функция представляет выбор основных функций бота
     *
     * @param argv входной запрос
     */
    public static void main(String[] argv) throws IOException, ParseException {
        System.out.println("Для создания пользователя выберете /newUser");
        while (true) {
            System.out.println("Введите одну из команд: /help, /newUser, /CardOfTheDestiny, /CardOfTheDay, /possibility");
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            Commands c = new Commands();
            c.command(command);
        }
        //цикл, который бесконечно будет запрашивать команду
    }
}