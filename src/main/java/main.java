import commands.commands;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;
//название пакета.название класса

/** Основной класс */
class main {
    /**
     * Функция представляет выбор основных функций бота
     * @param argv входной запрос
     */
    public static void main(String[] argv) throws IOException, ParseException {
        System.out.println("Для создания пользователя выберете /newUser");
        while (true) {
            System.out.println("Введите одну из команд: /help, /newUser, /cardOfTheDestiny, /cardOfTheDay, /possibility");
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();
            commands c = new commands();
            c.command(command);
        }
        //цикл, который бесконечно будет запрашивать команду
    }
}