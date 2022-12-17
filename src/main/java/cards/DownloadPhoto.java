package cards;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс Парсер карт
 */
public class DownloadPhoto {
    /**
     * Функция загрузки карт
     *
     * @param href название карты
     */
    public void savePhoto(String href) throws IOException {
        String urlImg = "https://astrohelper.ru/images/gadaniya/arkany/" + href + ".jpg";
        String fileDownload = "photo/" + href + ".jpg";
        FileOutputStream out = null;
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new URL(urlImg).openStream());
            out = new FileOutputStream(fileDownload);
            byte[] data = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                out.write(data, 0, count);
                out.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }
}
