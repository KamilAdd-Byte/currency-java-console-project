package currencyapp.nbpconnections.gold;

import currencyapp.nbpconnections.model.JsonLine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbpValueGoldTest {

    @Test
    void shouldJsonLineItsEmpty() {
        //given
        NbpValueGold nbpValueGold = new NbpValueGold();
        JsonLine jsonLine = nbpValueGold.getEmptyJsonLine();
        assertEquals(jsonLine.getValue(),"");
    }
}
