package currencyapp.jsoupcode;

import currencyapp.CurrencyLogicApp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Jsoup:
 * 1. Connection jsoup with url basic .get()
 * 2. Document and get elements by ...
 * 3. ForEach select your fields tag, attribute
 */
public class AbstractJsoupProcessor{

    private static final String BASIC_URL = BasicAppUrl.getUrlBasicCodeCurrency();
    private static List<String> codes;

    public void getCodeWithWiki() {
        try {
            Document document = Jsoup.connect(BASIC_URL).get();
            Elements elements = document.getElementsByClass("wikitable sortable");
            Elements title = document.getElementsByTag("p");
            System.out.println("_________________________________________");
            System.out.println(title.text());
            System.out.println("_________________________________________");

            for (Element element : elements.select("tr")) {
                System.out.println(element.text());
            }
            System.out.println(CurrencyLogicApp.getUserName() + " wybierz interesujący Cię kod waluty i wyszukaj wartości w opcji 3:");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
