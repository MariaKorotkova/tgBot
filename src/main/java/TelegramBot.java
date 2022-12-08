import cards.ParserForCards;
import commands.Commands;
import keyboard.Horo;
import keyboard.MakeKeyboard;
import keyboard.Taro;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public final class TelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    String status = " ";
    String photoName = " ";

    public TelegramBot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void sendPhoto(String filename, Long chatId) {
        java.io.File file = new java.io.File("photo", filename + ".jpg");
        InputFile f = new InputFile(file);
        SendPhoto sendPhoto = new SendPhoto(chatId.toString(),f);
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        file.delete();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            String command = update.getMessage().getText();
            Commands c = new Commands();
            String text = "";
            switch (command) {
                case "/keyboard" -> {
                    MakeKeyboard.createKeyboard(chatId);
                    try {
                        execute(MakeKeyboard.message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "Гороскоп" -> {
                    Horo.createHoro(chatId);
                    try {
                        execute(MakeKeyboard.message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "Карты Таро" -> {
                    Taro.createTaro(chatId);
                    try {
                        execute(MakeKeyboard.message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> {
                    try {
                        String[] answer = c.command(command, status);
                        text = answer[0];
                        status = answer[1];
                    } catch (IOException | ParseException e) {
                        throw new RuntimeException(e);
                    }
                    message.setChatId(chatId);
                    message.setText(text);
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            Commands c = new Commands();
            String text;
            SendMessage msq = new SendMessage();
            SendMessage message = new SendMessage();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            msq.setText(update.getCallbackQuery().getData());
            msq.setChatId(update.getCallbackQuery().getMessage().getChatId());

            String call = msq.getText();
            String com = "";
            switch (call) {
                case "Гороскоп на день":
                    com = "/horoscope_of_the_day";
                    break;
                case "Карта дня":
                    com = "/card_of_the_day";
                    break;
                case "Карта судьбы":
                    com = "/card_of_the_destiny";
                    break;
                case "Предсказание":
                    com = "/possibility";
                    break;
            }
            String[] answer = new String[0];
            try {
                answer = c.command(com, status);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            text = answer[0];
            status = answer[1];
            if (answer.length != 2){
                photoName = answer[2];
                System.out.println(photoName);
                try {
                    ParserForCards.ParseImg(photoName);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sendPhoto(photoName, chatId);
                message.setChatId(chatId);
                message.setText(text);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            } else {
                message.setChatId(chatId);
                message.setText(text);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
