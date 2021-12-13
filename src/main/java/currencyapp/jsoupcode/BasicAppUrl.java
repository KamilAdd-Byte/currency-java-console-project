package currencyapp.jsoupcode;

/**
 * Class has a public constructor that you can pass url to jsoup scrapping.
 */
public abstract class BasicAppUrl {

    private String urlForScrapping;
    private static final String URL_BASIC_CODE_CURRENCY = "https://pl.wikipedia.org/wiki/ISO_4217";
    private static final String URL_BASIC_VALUE_GOLD = "https://api.nbp.pl/api/cenyzlota/last/30/?format=json";

    protected BasicAppUrl(String urlForScrapping) {
        this.urlForScrapping = urlForScrapping;
    }

    public static String getUrlBasicCodeCurrency() {
        return URL_BASIC_CODE_CURRENCY;
    }

    public static String getUrlBasicValueGold() {
        return URL_BASIC_VALUE_GOLD;
    }

    public String getUrlForScrapping() {
        return urlForScrapping;
    }

}
