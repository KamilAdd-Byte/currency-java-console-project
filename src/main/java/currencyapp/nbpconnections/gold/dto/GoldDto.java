package currencyapp.nbpconnections.gold.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({"value","date"})
public class GoldDto {

    private String value;
    private LocalDate date;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cena złota (za 1g w próbie 1000){" +
                "wartość: '" + value + '\'' +
                ", data: " + date +
                '}';
    }
}
