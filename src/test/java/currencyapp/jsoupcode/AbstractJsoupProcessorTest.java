package currencyapp.jsoupcode;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

class AbstractJsoupProcessorTest {

    @Test
    void shouldGetCodeWithWikiTestStatusCode() {
        when().get(BasicAppUrl.getUrlBasicCodeCurrency())
                .then().statusCode(200);

    }

    @Test
    void shouldGetValueGoldAndTestStatusCode() {
        when().get(BasicAppUrl.getUrlBasicValueGold())
                .then().statusCode(200);

    }
}
