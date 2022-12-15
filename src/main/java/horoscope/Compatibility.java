package horoscope;

import com.vdurmont.emoji.EmojiParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;

public class Compatibility {
    public String compatibilityOfSigns(String name) {
        HashMap<String, String> nameOfSigns = new HashMap<String, String>();
        nameOfSigns.put("Овен", "oven");
        nameOfSigns.put("Телец", "telec");
        nameOfSigns.put("Близнецы", "bliznecy");
        nameOfSigns.put("Рак", "rak");
        nameOfSigns.put("Лев", "lev");
        nameOfSigns.put("Дева", "deva");
        nameOfSigns.put("Скорпион", "skorpion");
        nameOfSigns.put("Стрелец", "strelec");
        nameOfSigns.put("Козерог", "kozerog");
        nameOfSigns.put("Водолей", "vodolej");
        nameOfSigns.put("Рыбы", "ryby");
        nameOfSigns.put("Весы", "vesy");

        String str1 = ":purple_heart:Любовь и знакомство:purple_heart:\n\n";
        String result1 = EmojiParser.parseToUnicode(str1);

        String str2 = "\n\n:purple_heart:Отношения и семья:purple_heart:\n\n";
        String result2 = EmojiParser.parseToUnicode(str2);

        try {
            String[] signs = name.split(" ");
            String sign1 = signs[0];
            String gender1 = signs[1];
            String sign2 = signs[2];
            String gender2 = signs[3];
            String key1 = sign1.substring(0, 1).toUpperCase() + sign1.substring(1);
            String key2 = sign2.substring(0, 1).toUpperCase() + sign2.substring(1);

            if (gender1.equals("ж") && gender2.equals("м")) {
                Document document = Jsoup.connect("https://goroskop365.ru/sovmestimost/zhenshchina-"
                                + nameOfSigns.get(key1) + "-i-muzhchina-"
                                + nameOfSigns.get(key2) + "/")
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements div = document.select("div.sovmestblock p");
                String family = "";
                for (int i = 2; i < div.size(); i++) {
                    family += div.get(i).text();
                }
                String compatibility = result1 + div.get(0).text()
                        + result2 + family;
                return compatibility;
            } else if (gender1.equals("м") && gender2.equals("ж")) {
                Document document = Jsoup.connect("https://goroskop365.ru/sovmestimost/zhenshchina-"
                                + nameOfSigns.get(key2) + "-i-muzhchina-"
                                + nameOfSigns.get(key1) + "/")
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements div = document.select("div.sovmestblock p");
                String family = "";
                for (int i = 2; i < div.size(); i++) {
                    family += div.get(i).text();
                }
                String compatibility = result1 + div.get(0).text()
                        + result2 + family;
                return compatibility;
            } else {
                return "Введите знаки правильно!";
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return " ";
    }
}
