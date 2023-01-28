package currencyapp.nbpconnections.currency;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import currencyapp.nbpconnections.currency.dto.CurrencyDto;
import currencyapp.nbpconnections.model.JsonLine;
import currencyapp.nbpconnections.model.JsonLineValue;
import currencyapp.nbplogicparents.NbpLogicProcessor;
import io.vavr.control.Try;
import lombok.NoArgsConstructor;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

@NoArgsConstructor
public class NbpValueCurrency extends NbpLogicProcessor implements JsonLineValue {

    private static JsonLine jsonLine;

    private static String table;
    private static String currency;
    private static LocalDate date;

    public NbpValueCurrency(JsonLine jsonLine) {
        NbpValueCurrency.jsonLine = jsonLine;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        NbpValueCurrency.table = table;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        NbpValueCurrency.currency = currency;
    }

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        NbpValueCurrency.date = date;
    }

    public void getCurrencyValueOnNbpApi() throws FileNotFoundException {
            jsonLine = getJsonLine();

            Gson gson = new Gson();
            CurrencyDto currency = gson.fromJson(jsonLine.getValue(), CurrencyDto.class);
            printCurrencyValue(currency);

    }

    @Override
    public JsonLine getJsonLineItemByURL() throws IOException {
        var urlNbp = "https://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + date;
        URL onNbp = setUrlToAccessDeniedOnNbp(urlNbp);
        URLConnection urlConnection = setConnectionForUrl(onNbp);
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        jsonLine = new JsonLine();
        jsonLine.setValue(reader.readLine());
        return jsonLine;
    }

    @Override
    public JsonLine getJsonLine() {
        return Try.of(this::getJsonLineItemByURL)
                .onFailure(Throwable::printStackTrace)
                .getOrElse(JsonLine.empty());
    }

    private static void printCurrencyValue(CurrencyDto currency) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Pozyskane dane: ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(currency);
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
    }

    public static void printCurrencyToCsv() throws IOException {

        File file = new File("currency.csv");

        CsvMapper mapperCsv = new CsvMapper(); // instancja CsvMappera
        mapperCsv.enable(CsvParser.Feature.EMPTY_STRING_AS_NULL); //pomijanie nierozpoznanych typów

        CsvSchema columns = CsvSchema.builder().setUseHeader(true) //utworzenie kolumn
                .addColumn("table")
                .addColumn("currency")
                .addColumn("code")
                .addColumn("rates")
                .addColumn("no")
                .addColumn("effectiveDate")
                .addColumn("mid")
                .addColumn("bid")
                .addColumn("ask")
                .build();

        ObjectWriter writer = mapperCsv.writerWithSchemaFor(CurrencyDto.class).with(columns);
        writer.writeValues(file).write(currency);
    }

    /**
     * @param output Value to scanner.in
     * @return LocalDate pattern yyyy-MM-DD.
     */
    public static LocalDate convertOutputLineToDate(String output){
        return LocalDate.parse(output);
    }
}
