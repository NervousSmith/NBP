package kowalski.pawel.nbp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class NbpDataRequester implements DataRequester{

	private final String nbpApiLinkSingleRate = "http://api.nbp.pl/api/exchangerates/rates/a";
	private String currencyCode;
	private LocalDate date;
	
	public NbpDataRequester(String currencyCode, LocalDate date) {
		super();
		this.currencyCode = currencyCode;
		this.date = date;
	}
	
	@Override
	public InputStreamReader requestData() {
		return getStream();
	}
	
	private InputStreamReader getStream() {
		try {
			return new InputStreamReader(createConnection().getInputStream());
		} catch (IOException e) {
			//TODO add logger
			return null;
		}
	}
	
	private HttpURLConnection createConnection() {
		try {
			URL url = createLink();
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			return conn;
		} catch (IOException e) {
			// TODO add logger 
			return null;
		} 
	}
	
	private URL createLink() {
		try {
			return new URL(nbpApiLinkSingleRate + "/" + currencyCode + "/" + date);
		} catch (MalformedURLException e) {
			// TODO add logger 
			return null;
		}
	}

}
