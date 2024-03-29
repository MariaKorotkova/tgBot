package keyboard;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Гороскоп
 */
public class HoroscopeKeyboard extends Keyboard {
    /**
     * Функция создания кнопки Гороскоп
     *
     * @param chat_id идентификатор чата
     */
    public void getHoroscopeKeyboard(long chat_id) {
        message.setChatId(chat_id);
        message.setText(EmojiParser.parseToUnicode(":sparkles:Гороскоп:sparkles:"));

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> button = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        List<InlineKeyboardButton> row4 = new ArrayList<>();

        row1.add(InlineKeyboardButton.builder().text(EmojiParser.parseToUnicode(":crystal_ball:Гороскоп на день:crystal_ball:")).callbackData("Гороскоп на день").build());
        button.add(row1);
        row2.add(InlineKeyboardButton.builder().text(EmojiParser.parseToUnicode(":crystal_ball:Совместимость:crystal_ball:")).callbackData("Совместимость").build());
        button.add(row2);
        row3.add(InlineKeyboardButton.builder().text(EmojiParser.parseToUnicode(":crystal_ball:Музыка для знаков зодиака:crystal_ball:")).callbackData("Музыка для знаков зодиака").build());
        button.add(row3);
        row4.add(InlineKeyboardButton.builder().text(EmojiParser.parseToUnicode(":crystal_ball:График дня:crystal_ball:")).callbackData("График дня").build());
        button.add(row4);

        markup.setKeyboard(button);
        message.setReplyMarkup(markup);
    }

}
