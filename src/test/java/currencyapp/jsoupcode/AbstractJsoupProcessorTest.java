package currencyapp.jsoupcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AbstractJsoupProcessorTest.class)
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
