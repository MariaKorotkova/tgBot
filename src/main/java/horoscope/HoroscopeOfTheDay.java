package horoscope;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

public class HoroscopeOfTheDay {
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
        try {
            Document document = Jsoup.connect("https://7days.ru/astro/horoscope/"
                            + signs.get(name.substring(0, 1).toUpperCase()
                            + name.substring(1)) + "/today")
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                    .get();
            Elements div = document.select("div.horoscope-7days__content_text");
            String prediction = div.text();
            return prediction;
        } catch (Exception e) {
            return "Введите знак корректно!";
        }
    }
}
