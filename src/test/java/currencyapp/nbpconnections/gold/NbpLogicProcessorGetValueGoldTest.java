package currencyapp.nbpconnections.gold;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbpLogicProcessorGetValueGoldTest {

    @Test
    void shouldJsonLineItsEmpty() {
        //given
        String jsonLine = NbpLogicProcessorGetValueGold.getJsonLine();
        assertEquals(jsonLine,"");
    }
}
