package nbpconnections.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({"table","currency","code","rates"})
public class CurrencyDto {

    private String table;
    private String currency;
    private String code;
    private List<RatesDto> rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RatesDto> getRates() {
        return rates;
    }

    public void setRates(List<RatesDto> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currency: " +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates;
    }
}
