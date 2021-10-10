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
import java.util.stream.Stream;

public class NbpLogicProcessorGetValueGold extends NbpLogicProcessor {

    private static String jsonLine = "";
    private static Gson gson = null;
    private static BufferedReader reader;
    private static File file = new File("gold.csv");

    public NbpLogicProcessorGetValueGold(String jsonLine) {
        super(jsonLine);
    }

    public static String getJsonLine() {
        return jsonLine;
    }

    public static void setJsonLine(String jsonLine) {
        NbpLogicProcessorGetValueGold.jsonLine = jsonLine;
    }

    public static void getGoldValue (){
        try {

            URL onNbp = NbpLogicProcessor.setUrlToAccessDeniedOnNbp(BasicAppUrl.getUrlBasicValueGold());
            URLConnection urlConnection = NbpLogicProcessor.setConnectionForUrl(onNbp);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((jsonLine = reader.readLine()) != null){
                break;
            }
            gson = new Gson();
            GoldDto[] goldDto = gson.fromJson(jsonLine, GoldDto[].class);
            for (GoldDto dto : goldDto) {
                System.out.println(dto);
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printValueGoldToCsv() throws IOException {

        getGoldValue(); // method call

        gson = new Gson();

        CsvMapper mapperCsv = new CsvMapper(); // instancja CsvMappera
        mapperCsv.enable(CsvParser.Feature.EMPTY_STRING_AS_NULL); //pomijanie nierozpoznanych typów

        CsvSchema columns = CsvSchema.builder().setUseHeader(true) //utworzenie kolumn
                .addColumn("data")
                .addColumn("cena")
                .build();

        ObjectWriter writer = mapperCsv.writerWithSchemaFor(GoldDto.class).with(columns);


        GoldDto[] goldDto = gson.fromJson(jsonLine, GoldDto[].class);

        checkCsvFileAndWriteNewGoldValue(file, writer, goldDto);
    }


    private static void checkCsvFileAndWriteNewGoldValue(File file, ObjectWriter writer, GoldDto[] goldDto) throws IOException {
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

    public static void clearCsvFile(){
        System.out.println("Metoda clear!");
        try {
            reader = new BufferedReader(new FileReader(file));
            int line = reader.read();
            while (line!=0){

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
