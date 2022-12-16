package horoscope;

import java.util.HashMap;

public class MusicForZodiac {
    public String getMusic(String name) {
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
        return signs.get(key);
    }
}
