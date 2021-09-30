package currencyapp.nbpconnections.gold;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import currencyapp.jsoupcode.BasicAppUrl;
import currencyapp.nbpconnections.gold.dto.GoldDto;
import currencyapp.nbplogicparents.NbpLogicProcessor;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NbpLogicProcessorGetValueGold extends NbpLogicProcessor {

    private static String jsonLine = "";

    public NbpLogicProcessorGetValueGold(String jsonLine) {
        super(jsonLine);
    }

    public static void getGoldValue (){
        try {

            URL onNbp = NbpLogicProcessor.setUrlToAccessDeniedOnNbp(BasicAppUrl.getUrlBasicValueGold());
            URLConnection urlConnection = NbpLogicProcessor.setConnectionForUrl(onNbp);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            while ((jsonLine = reader.readLine()) != null){
                break;
            }

            Gson gson = new Gson();


            File file = new File("gold.csv");

            CsvMapper mapperCsv = new CsvMapper(); // instancja CsvMappera
            mapperCsv.enable(CsvParser.Feature.EMPTY_STRING_AS_NULL); //pomijanie nierozpoznanych typów

            CsvSchema columns = CsvSchema.builder().setUseHeader(true) //utworzenie kolumn
                    .addColumn("data")
                    .addColumn("cena")
                    .build();

            ObjectWriter writer = mapperCsv.writerWithSchemaFor(GoldDto.class).with(columns);

            GoldDto[] goldDto = gson.fromJson(jsonLine, GoldDto[].class);

            for (GoldDto dto : goldDto) {
                System.out.println(dto);
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
