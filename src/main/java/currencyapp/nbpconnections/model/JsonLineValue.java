package currencyapp.nbpconnections.model;

import java.io.IOException;

public interface JsonLineValue {

    JsonLine getJsonLineItemByURL() throws IOException ;

    JsonLine getJsonLine();

}
