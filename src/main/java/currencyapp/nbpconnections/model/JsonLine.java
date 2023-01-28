package currencyapp.nbpconnections.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JsonLine {
   private String value;

    public static JsonLine empty() {
        return new JsonLine("");
    }

    private JsonLine (String value) {
        this.value = value;
    }
    public static JsonLine of (String readLine) {
        return new JsonLine(readLine);
    }
}
