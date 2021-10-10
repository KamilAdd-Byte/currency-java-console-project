package currencyapp.nbpconnections.currency;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import currencyapp.nbpconnections.currency.dto.CurrencyDto;
import currencyapp.nbpconnections.currency.dto.RatesDto;
import currencyapp.nbplogicparents.NbpLogicProcessor;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public abstract class NbpLogicProcessorGetValueCurrency extends NbpLogicProcessor {

    private static String jsonLine;

    private static String table;
    private static String currency;
    private static LocalDate date;

    public NbpLogicProcessorGetValueCurrency(String jsonLine) {
        super(jsonLine);
    }

    public static String getJsonLine() {
        return jsonLine;
    }

    public static void setJsonLine(String jsonLine) {
        NbpLogicProcessorGetValueCurrency.jsonLine = jsonLine;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        NbpLogicProcessorGetValueCurrency.table = table;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        NbpLogicProcessorGetValueCurrency.currency = currency;
    }

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        NbpLogicProcessorGetValueCurrency.date = date;
    }

    public static void getCurrencyValueOnNbpApi() throws FileNotFoundException {
        try {
            var urlNbp = "https://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + date;
            URL onNbp = NbpLogicProcessor.setUrlToAccessDeniedOnNbp(urlNbp);
            URLConnection urlConnection = NbpLogicProcessor.setConnectionForUrl(onNbp);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            jsonLine = "";

            while ((jsonLine = reader.readLine()) != null) {
                break;
            }
            Gson gson = new Gson();
            CurrencyDto currency = gson.fromJson(jsonLine, CurrencyDto.class);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Pozyskane dane: ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(currency);
            System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static void printCurrencyToCsv() throws IOException {
        getCurrencyValueOnNbpApi();

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
