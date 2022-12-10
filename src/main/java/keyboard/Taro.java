package keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static keyboard.Keyboard.message;

/**
 * Класс Таро
 */
public class Taro {
    /**
     * Функция создания кнопок
     *
     * @param chat_id идентификатор чата
     */
    public static void createTaro(long chat_id) {
        message.setChatId(chat_id);
        message.setText("Карты Таро");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> button = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();


        row2.add(InlineKeyboardButton.builder().text("Карта дня").callbackData("Карта дня").build());
        button.add(row2);
        row2.add(InlineKeyboardButton.builder().text("Карта судьбы").callbackData("Карта судьбы").build());
        button.add(row3);
        row1.add(InlineKeyboardButton.builder().text("Предсказание").callbackData("Предсказание").build());
        button.add(row1);

        markup.setKeyboard(button);
        message.setReplyMarkup(markup);
    }
}
