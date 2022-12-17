package horoscope;

import com.vdurmont.emoji.EmojiParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;

/**
 * Класс гороскоп дня
 */
public class HoroscopeOfTheDay {
    /**
     * Функция получения гороскопа
     */
    public String horoscope(String name) {
        String key = name.substring(0, 1).toUpperCase()
                + name.substring(1);
        HashMapHoroscope sign = new HashMapHoroscope();
        try {
            Document document = Jsoup.connect("https://7days.ru/astro/horoscope/"
                            + sign.getZodiacFromHoroscope(key) + "/today")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements div = document.select("div.horoscope-7days__content_text");
            String str = ":" + sign.getZodiacFromHoroscope(key)
                    + ": " + name + "\n\n";
            String result = EmojiParser.parseToUnicode(str);
            return result + div.text();
        } catch (Exception e) {
            return "Введите знак корректно!";
        }
    }
}
