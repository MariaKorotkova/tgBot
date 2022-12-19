package horoscope;


/**
 * Класс Музыка для знаков зодиака
 */
public class MusicForZodiac {
    /**
     * Метод получения знака зодиака для загрузки аудио
     *
     * @param name знак зодиака на русском
     * @return возвращает знак зодиака на английском
     */
    public String getMusic(String name) {
        String key = name.substring(0, 1).toUpperCase()
                + name.substring(1);
        HashMapHoroscope sign = new HashMapHoroscope();
        return sign.getZodiacFromHoroscope(key);
    }
}
