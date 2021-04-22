package kowalski.pawel.nbp.NbpApi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import kowalski.pawel.nbp.interfaces.Currency;
import kowalski.pawel.nbp.interfaces.DataRequester;

public class NbpDataRequester implements DataRequester{

	private final String nbpApiLinkSingleRate = "http://api.nbp.pl/api/exchangerates/rates/a";
	private String currencyCode;
	private LocalDate date;
	
	@Override
	public InputStreamReader requestData(Currency currencyCode, LocalDate date) {
		this.currencyCode = currencyCode.name();
		this.date = date;
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
