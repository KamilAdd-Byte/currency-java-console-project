package restfull;

import currencyapp.jsoupcode.AbstractJsoupProcessor;
import currencyapp.CurrencyLogicApp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/coffees")
public class CoffeesResource {

    @GET
    @Path("orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> stringList(){
        return List.of("Marazm","Wersache","Prada");
    }

    public static void main(String[] args) {
        AbstractJsoupProcessor abstractJsoupProcessor = new AbstractJsoupProcessor();
        abstractJsoupProcessor.getCodeWithWiki();


        CurrencyLogicApp.getAscii();
    }
}
