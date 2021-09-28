package currencyapp;

import currencyapp.jsoupcode.AbstractJsoupProcessor;
import currencyapp.nbpconnections.NbpLogicProcessorGetValueCurrency;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;

public class CurrencyLogicApp implements Runnable {

    private static NbpLogicProcessorGetValueCurrency nbpLogicProcessorGetValueCurrency;

    private String title = "############# Zapytaj o wartość waluty, złota lub wskaźników giełdy ############# "+"\n" +
            "poszukaj wartości waluty wpisując wartość tabeli, kod waluty (po wybraniu opcji 2 otrzymasz małą ściągę wszystkich kodów)"+"\n"+
            "wpisując datę dowiesz sie jaką wartość dana waluta miała we wskazanym przez Ciebie dniu" + "\n"+
            "#######################################################################################"+"\n"+
            "Technologia: Java-11, Jsoup, nbp-api and JAX";
    private String version = "__________wersja 1.0____________";
    private String author = "_______@Kamil_Sulejewski__________";
    private static final StringBuilder ascii = new StringBuilder("\n" +
            "\n" +
            "__________                      __              __                           .__          __          \n" +
            "\\____    /____  ______ ___.__._/  |______      |__|   ____   __  _  _______  |  |  __ ___/  |_  ____  \n" +
            "  /     /\\__  \\ \\____ <   |  |\\   __\\__  \\     |  |  /  _ \\  \\ \\/ \\/ /\\__  \\ |  | |  |  \\   __\\/ __ \\ \n" +
            " /     /_ / __ \\|  |_> >___  | |  |  / __ \\_   |  | (  <_> )  \\     /  / __ \\|  |_|  |  /|  | \\  ___/ \n" +
            "/_______ (____  /   __// ____| |__| (____  /\\__|  |  \\____/    \\/\\_/  (____  /____/____/ |__|  \\___  >\n" +
            "        \\/    \\/|__|   \\/                \\/\\______|                        \\/                      \\/ \n" +
            "\n");
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
            System.out.println(getAscii());
            System.out.println("Wybierz opcję: " + "\n" + "<< 0 >> Wyjście z aplikacji" + "\n" + "<< 2 >> Ściąga z kodami walut (ISO 4217)" + "\n" + "<< 3 >> Szukaj wartości waluty" + "\n");
            userChoice = scanner.nextInt();

            switch (userChoice) {

                case SEARCH:
                    System.out.println(CurrencyLogicApp.userName + " wyszukaj interesujących Cię danych wg wzoru:");

                    try {
                        getChoiceCurrencyFields();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    try {
                        NbpLogicProcessorGetValueCurrency.getCurrencyValueOnNbpApi();
                    } catch (FileNotFoundException e) {
                        e.getMessage();
                    }

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
        System.out.println("Jak się do Ciebie zwracać?");
        String userName = scanner.nextLine();
        CurrencyLogicApp.userName = userName;
    }

    private void getChoiceCurrencyFields() throws ParseException {
       //select table
        System.out.println("Krok 1: Podaj wartość tabeli");
        String outputD = "";
        /**
         * && ->
         */
        while (!outputD.equals("A") && !outputD.equals("B") && !outputD.equals("C")){
            System.out.println("Możliwe wartości:  A   B   C" + "  *możesz podać z małej litery*");
            System.out.println();
            String outputTable = scanner.next().toUpperCase();
            outputD=outputTable;
        }
        NbpLogicProcessorGetValueCurrency.setTable(outputD.toUpperCase());

        //currency
        System.out.println("Krok 2: Podaj trzy literowy KOD waluty");
        String output = "";

        while (output.length()!=3) {
            System.out.println("Podaj poprawną wartość: wzór -> ABC" + "  *możesz podać z małej litery*");
            System.out.println();
            String outputCurrency = scanner.next();
            output = outputCurrency;
        }
        NbpLogicProcessorGetValueCurrency.setCurrency(output.toUpperCase());

        // date
        System.out.println("Krok 3: Podaj datę by otrzymać wartość wybranej przez siebie waluty:" + NbpLogicProcessorGetValueCurrency.getCurrency()+ " YYYY-mm-DD");
        String outputDate = scanner.next();
        LocalDate localDate = LocalDate.parse(outputDate);

        NbpLogicProcessorGetValueCurrency.setDate(localDate);
        System.out.println("Date user: "+localDate);
        scanner.nextLine();
    }

    public static StringBuilder getAscii() {
        return ascii;
    }
}
