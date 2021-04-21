package kowalski.pawel.nbp;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class RequestResponseHandler {
	
	private static final Logger logg = Logger.getLogger(RequestResponseHandler.class.getName());
	private final String nbpApiLinkSingleRate = "http://api.nbp.pl/api/exchangerates/rates/";

	
	public JSONObject getJson(String tableType, String currencyCode, LocalDate date) {
		date = checkIfDateIsInFuture(date);
		date = formatDateToProperForm(date);
		URL url;
		HttpURLConnection conn = null;
		InputStreamReader inputReader = null;
		BufferedReader br = null;
		
		try {
			url = new URL(nbpApiLinkSingleRate + tableType + "/" + currencyCode + "/" + date.toString() + "/");
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			inputReader = new InputStreamReader(conn.getInputStream());
			br = new BufferedReader(inputReader);
			String output = br.readLine();
			return new JSONObject(output);
		} catch(FileNotFoundException e) {
			if(!date.isBefore(LocalDate.of(2002, 1, 2))){
				return getJson(tableType, currencyCode, date.minusDays(1));
			}
			else {
				logg.log(Level.WARNING, "Not able to find any rate: ", e);
				return null;
			}
		} catch(IOException e) {
			logg.log(Level.WARNING, "Not able to receive information.", e);
			return null;
		} finally{
			conn.disconnect();
			closeStream(inputReader);
			closeStream(br);
		}
	}
	
	private LocalDate formatDateToProperForm(LocalDate date) {
		return LocalDate.parse(date.format(DateTimeFormatter.ISO_DATE));
	}
	
	private LocalDate checkIfDateIsInFuture(LocalDate date) {
		if(date.isAfter(LocalDate.now())) {
			return LocalDate.now();
		}
		else{
			return date;
		}
	}
	
	private void closeStream(Closeable s) {
			try {
				if(s != null) s.close();
			} catch (IOException e) {
				logg.log(Level.WARNING, "Not able to close stream" + s.getClass().getCanonicalName());
			}
	}
}
