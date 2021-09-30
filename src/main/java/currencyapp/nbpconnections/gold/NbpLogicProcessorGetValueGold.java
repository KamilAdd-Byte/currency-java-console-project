package currencyapp.nbpconnections.gold;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.google.gson.Gson;
import currencyapp.csvmapper.CsvProcessorWrite;
import currencyapp.jsoupcode.BasicAppUrl;
import currencyapp.nbpconnections.gold.dto.GoldDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class NbpLogicProcessorGetValueGold {
    private static List<GoldDto> goldDtoList;
    private static String jsonLine = "";

    public static void getGoldValue (){
        try {
            URL url = new URL(BasicAppUrl.getUrlBasicValueGold());
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((jsonLine = reader.readLine()) != null){
                break;
            }

            Gson gson = new Gson();
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
