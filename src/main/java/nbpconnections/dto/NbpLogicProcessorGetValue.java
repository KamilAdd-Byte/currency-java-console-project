package nbpconnections.dto;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.IntStream;

public abstract class NbpLogicProcessorGetValue {

    private static String jsonLine;

    private static String table;
    private static String currency;
    private static LocalDate date;

    public static String getJsonLine() {
        return jsonLine;
    }

    public static void setJsonLine(String jsonLine) {
        NbpLogicProcessorGetValue.jsonLine = jsonLine;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        NbpLogicProcessorGetValue.table = table;
    }

    public static void setCurrency(String currency) {
        NbpLogicProcessorGetValue.currency = currency;
    }

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        NbpLogicProcessorGetValue.date = date;
    }

    public static void getCurrencyValueOnNbpApi() throws FileNotFoundException {
        try {
            var urlNbp = "https://api.nbp.pl/api/exchangerates/rates/" + table + "/" + currency + "/" + date;
            URL url = new URL(urlNbp);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonLine = "";

            while ((jsonLine = reader.readLine()) != null) {
                break;
            }

            Gson gson = new Gson();
            CurrencyDto currency = gson.fromJson(jsonLine, CurrencyDto.class);
            System.out.println(currency);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    /**
     * @param output Value to scanner.in
     * @return LocalDate pattern yyyy-MM-DD.
     */
    public static LocalDate convertOutputLineToDate(String output){
        return LocalDate.parse(output);
    }
}
