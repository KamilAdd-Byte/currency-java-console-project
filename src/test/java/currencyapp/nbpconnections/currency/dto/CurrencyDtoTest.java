package currencyapp.nbpconnections.currency.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CurrencyDtoTest.class)
class CurrencyDtoTest {

    @Test
    @DisplayName("should this same fields set")
    void getTable() {
        //given
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setTable("A");
        currencyDto.setCurrency("USD");
        //when
        String dtoCurrency = currencyDto.getCurrency();
        //then
        assertNotEquals(dtoCurrency,"");
        assertEquals(dtoCurrency,"USD");
    }
}
