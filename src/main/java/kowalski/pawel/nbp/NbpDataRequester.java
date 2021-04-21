package kowalski.pawel.nbp;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;

public class NbpDataRequester implements DataRequester{

	private final String nbpApiLinkSingleRate = "http://api.nbp.pl/api/exchangerates/rates";
	private String currencyCode;
	private LocalDate date;
	
	public NbpDataRequester(String currencyCode, LocalDate date) {
		super();
		this.currencyCode = currencyCode;
		this.date = date;
	}
	
	@Override
	public InputStreamReader requestData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private HttpURLConnection connectionCreation() {
		try {
			URL url = new URL(createLink());
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			return conn;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private String createLink() {
		return nbpApiLinkSingleRate + "/" + currencyCode + "/" + date;
	}
	
	private boolean verifyUrl() {
		
	}


}
