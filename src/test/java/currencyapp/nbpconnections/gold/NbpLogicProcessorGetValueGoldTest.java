package currencyapp.nbpconnections.gold;

import currencyapp.nbpconnections.currency.model.JsonLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbpLogicProcessorGetValueGoldTest {

    @Test
    void shouldJsonLineItsEmpty() {
        //given
        JsonLine jsonLine = NbpLogicProcessorGetValueGold.getEmptyJsonLine();
        assertEquals(jsonLine.getValue(),"");
    }
}
