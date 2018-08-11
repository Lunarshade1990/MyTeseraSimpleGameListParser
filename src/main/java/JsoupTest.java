import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsoupTest {

    public static void main(String[] args) throws IOException {

        List<TestObj> objList = new ArrayList<TestObj>();

        File file = new File("C:\\Users\\lunar\\Documents\\myTeseraList.txt");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));

        Document doc = Jsoup.connect("https://tesera.ru/user/lunarshade/games/owns/all/").get();

        Elements h3Elements = doc.getElementsByAttributeValue("class", "text");

        for (Element element : h3Elements) {

            Element aElement = element.child(0).child(0);
            StringBuilder url = new StringBuilder("https://tesera.ru");
            url.append(aElement.attr("href"));

            Document doc2 = Jsoup.connect(url.toString()).get();
            Elements game = doc2.getElementsByAttributeValue("id", "game_title");

            String title = game.get(0).child(0).text();

            bf.append(title + System.getProperty("line.separator"));

            objList.add(new TestObj(title, url.toString()));
        }

        bf.close();
    }



}
