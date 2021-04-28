package kowalski.pawel.nbp.NbpApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import kowalski.pawel.nbp.apiInterfaces.DataProvider;

public class NbpDataProvider implements DataProvider{

	private final String nbpApiLink = "http://api.nbp.pl/api/exchangerates/rates/a";
	private String currencyCode;
	private LocalDate date;
	private NbpConnectionCreatorForJson connector = new NbpConnectionCreatorForJson();

	
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
		InputStreamReader readerWithData = 
				new InputStreamReader(connector.createConnection(createUrl()));
		return readerWithData;
	}
		
	private URL createUrl() {
		try {
			return new URL(nbpApiLink + "/" + currencyCode + "/" + date);
		} catch (MalformedURLException e) {
			// TODO add logger 
			return null;
		}
	}
}