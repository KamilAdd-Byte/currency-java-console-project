package loop;

import com.google.gson.Gson;
import restconnection.pojo.CurrencyPOJO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MainLoop implements Runnable {

    private static final int SEARCH = 1;
    private static final int EXIT = 0;
    private static String jsonLine;

    private static String userName;
    private static String table;
    private static String currency;
    private static String date;

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        getWelcomeUser();
        int userChoice=-1;
        while (userChoice != MainLoop.SEARCH && userChoice != MainLoop.EXIT){
            System.out.println("Wybierz opcję: 0 >> EXIT  lub 1 >> SEARCH Currency on REST");
            userChoice = scanner.nextInt();
        }
            do {
                switch (userChoice) {

                    case SEARCH: {
                        System.out.println(MainLoop.userName + " jesteś w opcji SEARCH. Wyszukaj interesujących Cię danych wg wzoru:");
                        getChoiceCurrencyFields();

                        try {
                            var urlNbp = "https://api.nbp.pl/api/exchangerates/rates/"+ table + "/" + currency + "/" + date;
                            URL url = new URL(urlNbp);
                            URLConnection connection = url.openConnection();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            jsonLine = "";

                            while ((jsonLine = reader.readLine()) != null){
                                break;
                            }

                            Gson gson = new Gson();
                            CurrencyPOJO currency = gson.fromJson(jsonLine,CurrencyPOJO.class);

                            System.out.println(currency);
                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }




                        break;
                    }
                    case EXIT: {
                        System.out.println("Wyjście z programu");
                        scanner.close();
                        break;
                    }
                    default:
                        System.out.println("Wybierz 1 by szukać kursu lub 2 by zamknąc program");
                }
            } while (userChoice != 0);
        }

    private void getWelcomeUser() {
        System.out.println("Welcome into java-api. Search and write exchange rate with REST-nbp");
        System.out.println("Please set your name");
        String userName = scanner.nextLine();
        MainLoop.userName = userName;
    }


    private void getChoiceCurrencyFields() {
        System.out.println("Set table: A, B, C");
        String outputTable = scanner.next();
        MainLoop.table = outputTable;
        System.out.println("Set currency");
        String outputCurrency = scanner.next();
        MainLoop.currency = outputCurrency;
        String currency = outputCurrency;
        System.out.println("Set date: YYYY-mm-DD");
        String outputDate = scanner.next();
        MainLoop.date = outputDate;
    }
}
