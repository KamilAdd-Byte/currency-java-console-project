package currencyapp.jsoupcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Basic app url test.
 */
@SpringBootTest(classes = BasicAppUrlTest.class)
class BasicAppUrlTest {

    /**
     * Gets url basic value gold.
     */
    @Test
    void getUrlBasicValueGold() {
        //given
        String urlBasicValueGold = BasicAppUrl.getUrlBasicValueGold();

        //then
        assertNotNull(urlBasicValueGold);
        assertEquals(urlBasicValueGold,"https://api.nbp.pl/api/cenyzlota/last/30/?format=json");
    }
    @Test
    @DisplayName("should status OK for USD currency")
    void getUrlBasicCodeWithWikipedia() {
        //given
        String urlBasicCodeCurrency = BasicAppUrl.getUrlBasicCodeCurrency();

        //then
        assertNotNull(urlBasicCodeCurrency);
        assertEquals(urlBasicCodeCurrency,"https://pl.wikipedia.org/wiki/ISO_4217");
    }
    @Test
    void shouldAddObject(){
        //given
        BasicAppUrl basicAppUrl = new BasicAppUrl("https://wp.pl") {
            @Override
            public String getUrlForScrapping() {
                return super.getUrlForScrapping();
            }
        };

        int hashCode = basicAppUrl.hashCode();

        assertEquals(hashCode,basicAppUrl.hashCode());
    }
    @Test
    @DisplayName("should status OK for USD currency")
    void getUSDonNbpApi(){
        when().get("https://api.nbp.pl/api/exchangerates/rates/A/USD/").
        then().statusCode(200);
    }


}
