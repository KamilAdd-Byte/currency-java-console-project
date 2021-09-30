package currencyapp.nbplogicparents;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author kamillodzinski
 * @implNote This is SuperClass
 */
public abstract class NbpLogicProcessor {

    private String jsonLine;
    private static URL url;
    private static URLConnection CONNECTION;

    public NbpLogicProcessor(String jsonLine) {
        this.jsonLine = jsonLine;
    }

    /**
     * @param otherUrl url to connections nbp.api or some else
     * @return url ready to provide urlConnections
     */
    public static URL setUrlToAccessDeniedOnNbp(String otherUrl) throws MalformedURLException {
        try {
            URL url = new URL(otherUrl);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new MalformedURLException();
    }

    /**
     * @param otherUrl url to connections
     * @return open to connections provide
     * @throws MalformedURLException
     */
    public static URLConnection setConnectionForUrl(URL otherUrl) throws MalformedURLException {
        try {
            return otherUrl.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new MalformedURLException();
    }
}
