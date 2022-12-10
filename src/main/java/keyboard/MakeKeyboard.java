package keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Создание Клавиатуры
 */
public class MakeKeyboard extends Keyboard {
    /**
     * Функция создания клавиатуры
     *
     * @param chat_id идентификатор чата
     */
    public static void createKeyboard(long chat_id) {

        message.setChatId(chat_id);
        message.setText("Клава");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Гороскоп");
        row.add("Карты Таро");
        keyboard.add(row);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
    }
}
