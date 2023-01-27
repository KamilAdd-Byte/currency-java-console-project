package currencyapp.nbplogicparents;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Kamil Sulejewski
 */

public abstract class NbpLogicProcessor {

    private static URL url;

    public NbpLogicProcessor() {}

    /**
     * @param otherUrl url to connections nbp.api or some else
     * @return url ready to provide urlConnections
     */
    public static URL setUrlToAccessDeniedOnNbp(String otherUrl) throws MalformedURLException {
        try {
            url = new URL(otherUrl);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new MalformedURLException();
    }

    /**
     * @param otherUrl url to connectionsimport lombok.Value;
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
