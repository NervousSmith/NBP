package kowalski.pawel.nbp.NbpApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NbpConnectionCreatorForJson {

	public InputStream createConnection(URL url) {
		try {
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			return conn.getInputStream();
		} catch (IOException e) {
			return null;
		}
	}
}
