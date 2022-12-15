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
     *
     * @param name знак зодиака
     */
    public String horoscope(String name) {
        HashMap<String, String> signs = new HashMap<String, String>();
        signs.put("Овен", "aries");
        signs.put("Телец", "taurus");
        signs.put("Близнецы", "gemini");
        signs.put("Рак", "cancer");
        signs.put("Лев", "leo");
        signs.put("Дева", "virgo");
        signs.put("Скорпион", "scorpio");
        signs.put("Стрелец", "sagittarius");
        signs.put("Козерог", "capricorn");
        signs.put("Водолей", "aquarius");
        signs.put("Рыбы", "pisces");
        signs.put("Весы", "libra");
        String key = name.substring(0, 1).toUpperCase()
                + name.substring(1);
        try {
            Document document = Jsoup.connect("https://7days.ru/astro/horoscope/"
                            + signs.get(key) + "/today")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements div = document.select("div.horoscope-7days__content_text");
            String str = ":" + signs.get(key)
                    + ": " + name + "\n\n";
            String result = EmojiParser.parseToUnicode(str);
            String prediction = result + div.text();
            return prediction;
        } catch (Exception e) {
            return "Введите знак корректно!";
        }
    }
}
