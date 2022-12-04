package keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Horo extends Keyboard {

    public static void createHoro(long chat_id) {
        message.setChatId(chat_id);
        message.setText("Гороскоп");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> button = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();

        row1.add(InlineKeyboardButton.builder().text("Гороскоп на день").callbackData("Гороскоп на день").build());
        button.add(row1);

        markup.setKeyboard(button);
        message.setReplyMarkup(markup);
    }

}
