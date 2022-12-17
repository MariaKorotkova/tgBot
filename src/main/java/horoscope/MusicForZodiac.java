package horoscope;


public class MusicForZodiac {
    public String getMusic(String name) {
        String key = name.substring(0, 1).toUpperCase()
                + name.substring(1);
        HashMapHoroscope sign = new HashMapHoroscope();
        return sign.getZodiacFromHoroscope(key);
    }
}
