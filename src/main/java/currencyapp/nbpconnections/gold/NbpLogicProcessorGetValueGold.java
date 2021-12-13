package currencyapp.nbpconnections.gold;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import currencyapp.jsoupcode.BasicAppUrl;
import currencyapp.nbpconnections.gold.dto.GoldDto;
import currencyapp.nbplogicparents.NbpLogicProcessor;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class NbpLogicProcessorGetValueGold extends NbpLogicProcessor {

    private static String jsonLine = "";
    private static Gson gson = null;
    private static BufferedReader reader;
    private static File file;
    private static final Scanner scanner = new Scanner(System.in);

    public NbpLogicProcessorGetValueGold(String jsonLine) {
        super(jsonLine);
    }

    public static String getJsonLine() {
        return jsonLine;
    }

    public static void setJsonLine(String jsonLine) {
        NbpLogicProcessorGetValueGold.jsonLine = jsonLine;
    }

    public static void onlyPrintGoldValueInConsole() {
        try {

            URL onNbp = NbpLogicProcessor.setUrlToAccessDeniedOnNbp(BasicAppUrl.getUrlBasicValueGold());
            URLConnection urlConnection = NbpLogicProcessor.setConnectionForUrl(onNbp);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((jsonLine = reader.readLine()) != null) {
                break;
            }
            GoldDto[] goldDto = getGoldDtos();
            for (GoldDto dto : goldDto) {
                System.out.println(dto);
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static GoldDto[] getGoldDtos() {
        gson = new Gson();
        return gson.fromJson(jsonLine, GoldDto[].class);
    }

    public static void printValueGoldToCsv() throws IOException {

        onlyPrintGoldValueInConsole(); // method call

        gson = new Gson();

        ObjectWriter writer = getObjectWriter();
        GoldDto[] goldDto = getGoldDtos();

        createFileName();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine())!=null){
                writer.writeValues(file).writeAll(goldDto);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        reader.close();
    }

    private static ObjectWriter getObjectWriter() {
        CsvMapper mapperCsv = new CsvMapper(); // instancja CsvMappera
        mapperCsv.enable(CsvParser.Feature.EMPTY_STRING_AS_NULL); //pomijanie nierozpoznanych typów

        CsvSchema columns = CsvSchema.builder().setUseHeader(true) //utworzenie kolumn
                .addColumn("data")
                .addColumn("cena")
                .build();

        return mapperCsv.writerWithSchemaFor(GoldDto.class).with(columns);
    }

    private static void createFileName() {
        System.out.println("Nadaj nazwe plikowi:");
        String userFileName = scanner.nextLine();
        file = new File(userFileName+".csv");
        }

    private static void setFile(File file) {
        NbpLogicProcessorGetValueGold.file = file;
    }
}
