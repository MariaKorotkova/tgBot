package commands;

import java.util.HashMap;

/**
 * Класс Названия команд
 */
public class NameOfCommands {
    /**
     * Метод получения команды
     *
     * @param key название команды, введенной пользователем
     * @return возвращает команду
     */
    public String getCommand(String key) {
        HashMap<String, String> commands = new HashMap<>();
        commands.put("График дня", "/chart");
        commands.put("Музыка для знаков зодиака", "/music");
        commands.put("Гороскоп на день", "/horoscope_of_the_day");
        commands.put("Карта дня", "/card_of_the_day");
        commands.put("Карта судьбы", "/card_of_the_destiny");
        commands.put("Предсказание", "/possibility");
        commands.put("Совместимость", "/compatibility");
        return commands.get(key);
    }
}
