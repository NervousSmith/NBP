package kowalski.pawel.nbp.NbpApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import kowalski.pawel.nbp.apiInterfaces.DataRequester;

public class NbpDataRequester implements DataRequester{

	private final String nbpApiLinkSingleRate = "http://api.nbp.pl/api/exchangerates/rates/a";
	private String currencyCode;
	private LocalDate date;

	
	@Override
	public String requestData(String curremcyCode, LocalDate date) {
		this.currencyCode = curremcyCode;
		this.date = date;
		return readStream();
	}
	
	
	private String readStream() {
		BufferedReader bufferedReader;
		String output;
		try {
			bufferedReader = new BufferedReader(getStream());
			output = bufferedReader.readLine();
		} catch (IOException e) {
			output = "";
		}
		return output;
	}
	
	
	private InputStreamReader getStream() {
		try {
			return new InputStreamReader(createConnection().getInputStream());
		} catch (IOException e) {
			//TODO add logger
			e.printStackTrace();
			return null;
		}
	}
	
	private HttpURLConnection createConnection() {
		try {
			URL url = createUrl();
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			return conn;
		} catch (IOException e) {
			return null;
		} 
	}
	
	private URL createUrl() {
		try {
			return new URL(nbpApiLinkSingleRate + "/" + currencyCode + "/" + date);
		} catch (MalformedURLException e) {
			// TODO add logger 
			return null;
		}
	}
}
