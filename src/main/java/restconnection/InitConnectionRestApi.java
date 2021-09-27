package restconnection;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import nbpconnections.dto.CurrencyDto;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class InitConnectionRestApi {

    private static String table;
    private static String currency;
    private static String date;
    private static final Scanner scanner = new Scanner(System.in);
    private static String jsonLine;

    public static void main(String[] args) throws IOException {
        System.out.println("Set table: A, B, C");
        String outputTable = scanner.next();
        table=outputTable.toUpperCase();
        System.out.println("Set currency");
        String outputCurrency = scanner.next();
        currency=outputCurrency;
        System.out.println("Set date: YYYY-mm-DD");
        String outputDate = scanner.next();
        date=outputDate.toUpperCase();

        try {
            String urlNbp = "https://api.nbp.pl/api/exchangerates/rates/"+ table + "/" + currency + "/" + date;
            URL url = new URL(urlNbp);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonLine = "";

            while ((jsonLine=reader.readLine()) != null){
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CurrencyDto currencyDto = getCurrencyPOJO();

        int length = currencyDto.toString().length();
        String[] splitCurrencyValue = new String[length];

        File file = new File("currency.csv");

        CsvMapper mapperCsv = new CsvMapper(); // instancja CsvMappera
        mapperCsv.enable(CsvParser.Feature.WRAP_AS_ARRAY); //pomijanie nierozpoznanych typów

        CsvSchema columns = CsvSchema.builder().setUseHeader(true) //utworzenie kolumn
                .addColumn("table")
                .addColumn("currency")
                .addColumn("code")
                .addArrayColumn("rates")
                .addColumn("no")
                .addColumn("effectiveDate")
                .addColumn("mid")
                .addColumn("bid")
                .addColumn("ask")
                .build();

        ObjectWriter writer = mapperCsv.writerWithSchemaFor(CurrencyDto.class).with(columns);




        writer.writeValues(file).write(currencyDto);




    }


    private static CurrencyDto getCurrencyPOJO() {
        Gson gson = new Gson();
        CurrencyDto currencyDto = gson.fromJson(jsonLine, CurrencyDto.class);
        return currencyDto;
    }
}
