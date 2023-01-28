package currencyapp;

import currencyapp.jsoupcode.AbstractJsoupProcessor;
import currencyapp.nbpconnections.currency.NbpValueCurrency;
import currencyapp.nbpconnections.gold.NbpValueGold;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class CurrencyLogicApp implements Runnable {
    private static NbpValueCurrency nbpValueCurrency;
    private final String title = "############# Zapytaj o wartość waluty, złota lub wskaźników giełdy ############# "+"\n" +
            "poszukaj wartości waluty wpisując wartość tabeli, kod waluty (po wybraniu opcji 2 otrzymasz małą ściągę wszystkich kodów)"+"\n"+
            "wpisując datę dowiesz sie jaką wartość dana waluta miała we wskazanym przez Ciebie dniu" + "\n"+
            "#######################################################################################"+"\n"+
            "Technologia: Java-11, Jsoup, nbp-api and JAX";
    private final String version = "__________wersja 1.0____________";
    private final String author = "_______@Kamil_Sulejewski__________";
    private static final StringBuilder ascii = new StringBuilder("\n" +
            "\n" +
            "__________                      __              __                           .__          __          \n" +
            "\\____    /____  ______ ___.__._/  |______      |__|   ____   __  _  _______  |  |  __ ___/  |_  ____  \n" +
            "  /     /\\__  \\ \\____ <   |  |\\   __\\__  \\     |  |  /  _ \\  \\ \\/ \\/ /\\__  \\ |  | |  |  \\   __\\/ __ \\ \n" +
            " /     /_ / __ \\|  |_> >___  | |  |  / __ \\_   |  | (  <_> )  \\     /  / __ \\|  |_|  |  /|  | \\  ___/ \n" +
            "/_______ (____  /   __// ____| |__| (____  /\\__|  |  \\____/    \\/\\_/  (____  /____/____/ |__|  \\___  >\n" +
            "        \\/    \\/|__|   \\/                \\/\\______|                        \\/                      \\/ \n" +
            "\n");
    private static final int EXIT = 0;
    private static final int CODE = 2;
    private static final int SEARCH = 3;
    private static final int GOLD = 4;
    private static final int CSV = 5;
    private static final int SHOW = 6;


    private static String userName;
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        getApiInfo();
        getWelcomeUser();

        int userChoice = 8;

        while (userChoice!=0){
            System.out.println(getAscii());
            System.out.println("Wybierz opcję: " + "\n" + "<< 0 >> Wyjście z aplikacji" + "\n" + "<< 2 >> Ściąga z kodami walut (ISO 4217)" + "\n" + "<< 3 >> Szukaj wartości waluty" + "\n" + "<< 4 >> Pokaż 30 ostatnich cen złota");
            userChoice = scanner.nextInt();

            switch (userChoice) {
                //case search currency value from nbp.api
                case SEARCH:
                    System.out.println(CurrencyLogicApp.userName + " wyszukaj interesujących Cię danych wg wzoru:");
                    try {
                        getChoiceCurrencyFields();
                        NbpValueCurrency nbpValueCurrency = new NbpValueCurrency();
                        nbpValueCurrency.getCurrencyValueOnNbpApi();
                    } catch (FileNotFoundException e) {
                        e.getMessage();
                    }
                    break;
                    //Code - currency value
                case CODE:
                    AbstractJsoupProcessor ajp = new AbstractJsoupProcessor();
                    ajp.getCodeWithWiki();
                    break;
                //Gold - gold value
                case GOLD:
                    System.out.println("Wybierz opcję: " + "\n" + "<< 5 >> Drukuj do CSV" + "\n" + "<< 6 >> Tylko wyświetl");
                    userChoice = scanner.nextInt();
//                case CSV:
//                        try {
//                            NbpLogicProcessorGetValueGold.printValueGoldToCsv();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        break;
                    // TODO: 11.10.2021 Fix CSV write method! 
                    case SHOW:
                        NbpValueGold nbpValueGold = new NbpValueGold();
                        nbpValueGold.onlyPrintGoldValueInConsole();
                        break;
                case EXIT:
                    System.out.println("Wyjście z programu");
                    scanner.close();
                    break;
                default:
                    System.out.println("Press 1 - search currency or 0 - close program");
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

    private void getChoiceCurrencyFields() {
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
        NbpValueCurrency.setTable(outputD.toUpperCase());

        //select currency
        System.out.println("Krok 2: Podaj trzy literowy KOD waluty");
        String output = "";

        while (output.length()!=3) {
            System.out.println("Podaj poprawną wartość: wzór -> ABC" + "  *możesz podać z małej litery*");
            System.out.println();
            String outputCurrency = scanner.next();
            output = outputCurrency;
        }
        NbpValueCurrency.setCurrency(output.toUpperCase());

        //select date
        System.out.println("Krok 3: Podaj datę by otrzymać wartość wybranej przez siebie waluty: <<||" + NbpValueCurrency.getCurrency() + "||>> wzór -> YYYY-mm-DD");
        String outputDate = scanner.next();
        LocalDate localDate = LocalDate.parse(outputDate);

        NbpValueCurrency.setDate(localDate);
        System.out.println("Date " + localDate + "given by " + userName);
        scanner.nextLine();

    }

    public static StringBuilder getAscii() {
        return ascii;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        CurrencyLogicApp.userName = userName;
    }
}
