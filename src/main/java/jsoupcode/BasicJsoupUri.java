package jsoupcode;

/**
 * Class has a public constructor that you can pass url to jsoup scrapping.
 */
public class BasicJsoupUri {
    private final String urlForScrapping;
    private static String URL_BASIC_CODE_CURRENCY = "https://pl.wikipedia.org/wiki/ISO_4217";

    public BasicJsoupUri(final String urlForScrapping) {
        this.urlForScrapping = urlForScrapping;
    }

    public static String getUrlBasicCodeCurrency() {
        return URL_BASIC_CODE_CURRENCY;
    }
}
