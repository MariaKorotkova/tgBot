package horoscope;

import com.vdurmont.emoji.EmojiParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;


public class Compatibility {
    public String getCompatibilityOfSigns(String name) {

        String str1 = ":purple_heart:Любовь и знакомство:purple_heart:\n\n";
        String result1 = EmojiParser.parseToUnicode(str1);

        String str2 = "\n\n:purple_heart:Отношения и семья:purple_heart:\n\n";
        String result2 = EmojiParser.parseToUnicode(str2);

        HashMapHoroscope sign = new HashMapHoroscope();

        try {
            ArrayList<String> signs = new ArrayList<>(Arrays.asList(name.split(" ")));
            String sign1 = signs.get(0);
            String gender1 = signs.get(1);
            String sign2 = signs.get(2);
            String gender2 = signs.get(3);
            String key1 = sign1.substring(0, 1).toUpperCase() + sign1.substring(1);
            String key2 = sign2.substring(0, 1).toUpperCase() + sign2.substring(1);

            if (gender1.equals("ж") && gender2.equals("м")) {
                Document document = Jsoup.connect("https://goroskop365.ru/sovmestimost/zhenshchina-"
                                + sign.getZodiacForCompability(key1) + "-i-muzhchina-"
                                + sign.getZodiacForCompability(key2) + "/")
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements div = document.select("div.sovmestblock p");
                StringBuilder family = new StringBuilder();
                for (int i = 2; i < div.size(); i++) {
                    family.append(div.get(i).text());
                }
                return result1 + div.get(0).text()
                        + result2 + family;
            } else if (gender1.equals("м") && gender2.equals("ж")) {
                Document document = Jsoup.connect("https://goroskop365.ru/sovmestimost/zhenshchina-"
                                + sign.getZodiacForCompability(key2) + "-i-muzhchina-"
                                + sign.getZodiacForCompability(key1) + "/")
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Elements div = document.select("div.sovmestblock p");
                StringBuilder family = new StringBuilder();
                for (int i = 2; i < div.size(); i++) {
                    family.append(div.get(i).text());
                }
                return result1 + div.get(0).text()
                        + result2 + family;
            } else {
                return "Введите знаки правильно!";
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return " ";
    }
}
