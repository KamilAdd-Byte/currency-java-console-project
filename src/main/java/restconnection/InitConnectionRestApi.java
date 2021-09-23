package restconnection;

import com.google.gson.Gson;
import restconnection.pojo.CurrencyPOJO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class InitConnectionRestApi {

    private static String table;
    private static String currency;
    private static String date;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Set table: A, B, C");
        String outputTable = scanner.next();
        table=outputTable.toUpperCase();
        System.out.println("Set currency");
        String outputCurrency = scanner.next();
        currency=outputCurrency;
        System.out.println("Set date: YYYY-dd-MM");
        String outputDate = scanner.next();
        date=outputDate.toUpperCase();

        try {
            String urlNbp = "https://api.nbp.pl/api/exchangerates/rates/"+ table + "/" + currency + "/" + date;
            URL url = new URL(urlNbp);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonLine = "";

            while ((jsonLine=reader.readLine()) != null){
                break;
            }

            System.out.println(jsonLine);

            Gson gson = new Gson();
            CurrencyPOJO currencyPOJO = gson.fromJson(jsonLine, CurrencyPOJO.class);

            System.out.println(currencyPOJO);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
