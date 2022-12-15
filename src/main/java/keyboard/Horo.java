package keyboard;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Гороскоп
 */
public class Horo extends Keyboard {
    /**
     * Функция создания кнопки Гороскоп
     *
     * @param chat_id идентификатор чата
     */
    public static void createHoro(long chat_id) {
        message.setChatId(chat_id);
        message.setText(EmojiParser.parseToUnicode(":sparkles:Гороскоп:sparkles:"));

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> button = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();

        row1.add(InlineKeyboardButton.builder().text(EmojiParser.parseToUnicode(":crystal_ball:Гороскоп на день:crystal_ball:")).callbackData("Гороскоп на день").build());
        button.add(row1);
        row2.add(InlineKeyboardButton.builder().text(EmojiParser.parseToUnicode(":crystal_ball:Совместимость:crystal_ball:")).callbackData("Совместимость").build());
        button.add(row2);

        markup.setKeyboard(button);
        message.setReplyMarkup(markup);
    }

}
