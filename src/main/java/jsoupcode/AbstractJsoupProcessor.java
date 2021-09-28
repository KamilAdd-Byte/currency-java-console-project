package jsoupcode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jsoup:
 * 1. Connection jsoup with url basic.
 * 2. Document and get elements
 * 3. ForEach select your fields
 */
public class AbstractJsoupProcessor{

    private static final String BASIC_URL = BasicJsoupUri.getUrlBasicCodeCurrency();
    private static List<String> codes;

    public void getCodeWithWiki() {
        try {
            Document document = Jsoup.connect(BASIC_URL).get();
            Elements elements = document.getElementsByClass("wikitable sortable");
            Elements title = document.getElementsByTag("p");
            System.out.println(title.text());

            for (Element element : elements.select("tr")) {
                System.out.println(element.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
