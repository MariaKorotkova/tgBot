package horoscope;

import java.util.HashMap;

public class HashMapHoroscope {
    public String getZodiacFromHoroscope(String key){
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
        return signs.get(key);
    }
    public String getZodiacForCompability(String key){
        HashMap<String, String> sign = new HashMap<String, String>();
        sign.put("Овен", "oven");
        sign.put("Телец", "telec");
        sign.put("Близнецы", "bliznecy");
        sign.put("Рак", "rak");
        sign.put("Лев", "lev");
        sign.put("Дева", "deva");
        sign.put("Скорпион", "skorpion");
        sign.put("Стрелец", "strelec");
        sign.put("Козерог", "kozerog");
        sign.put("Водолей", "vodolej");
        sign.put("Рыбы", "ryby");
        sign.put("Весы", "vesy");
        return sign.get(key);
    }
}
