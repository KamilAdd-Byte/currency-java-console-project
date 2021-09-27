package management;

import jsoupcode.AbstractJsoupProcessor;
import nbpconnections.dto.NbpLogicProcessorGetValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CurrencyLogicApp implements Runnable {

    private static NbpLogicProcessorGetValue nbpLogicProcessorGetValue;

    private String title = ">>>>>>>>>>>> REST API <<<<<<<<<<<<<<< "+"\n" +"search then you get currency value for day"+"\n"
            + "Use: Java-11, Jsoup, REST-Nbp connection and JAX";
    private String version = "__________version 1.0____________";
    private String author = "_______@Kamil_Sulejewski__________";

    private static final int CODE = 2;
    private static final int SEARCH = 3;
    private static final int EXIT = 0;
    private static String jsonLine;

    private static String userName;

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        getApiInfo();
        getWelcomeUser();
        int userChoice = 4;

        while (userChoice!=0){

            System.out.println("Wybierz opcję: " + "\n" + "0 >> EXIT" + "\n" + "2 >> GET all Code" + "\n" + "3 >> SEARCH Currency on REST" + "\n");
            userChoice = scanner.nextInt();

            switch (userChoice) {

                case SEARCH:
                    System.out.println(CurrencyLogicApp.userName + " jesteś w opcji SEARCH. Wyszukaj interesujących Cię danych wg wzoru:");
                    try {
                        getChoiceCurrencyFields();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    NbpLogicProcessorGetValue.getCurrencyValueOnNbpApi();
                    break;

                case EXIT:
                    System.out.println("Wyjście z programu");
                    scanner.close();
                    break;

                case CODE:
                    AbstractJsoupProcessor ajp = new AbstractJsoupProcessor();
                    ajp.getCodeWithWiki();
                    break;

                default:
                    System.out.println("Press 1 - search currency or 2 - close program");
            }
        }
    }

    private void getApiInfo() {
        System.out.println(title + "\n" + version + "\n" + author + "\n");
    }

    private void getWelcomeUser() {
        System.out.println("Welcome into java-rest-currency-api. Search and write exchange rate with REST-nbp");
        System.out.println("Please enter your name");
        String userName = scanner.nextLine();
        CurrencyLogicApp.userName = userName;
    }


    private void getChoiceCurrencyFields() throws ParseException {
       //select table
        System.out.println("Set table: A, B, C");
        String outputTable = scanner.next();
        NbpLogicProcessorGetValue.setTable(outputTable.toUpperCase());
        //currency
        System.out.println("Set currency");
        String outputCurrency = scanner.next();
        NbpLogicProcessorGetValue.setCurrency(outputCurrency.toUpperCase());
        // date
        System.out.println("Set date: YYYY-mm-DD");
        String outputDate = scanner.next();
// TODO: 27.09.2021 Date format! Fix this bug 
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(outputDate);
        
        NbpLogicProcessorGetValue.setDate(date);
        System.out.println("Date user: "+date);
    }
}
