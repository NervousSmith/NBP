package kowalski.pawel.nbp;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RatesCreator {

	public AskBidExchangeRates createAskBidExchangeRates(JSONObject receivedJson) {
		BigDecimal ask, bid;
		try {
			JSONArray gotMid = receivedJson.getJSONArray("rates");
			JSONObject tempJson = gotMid.getJSONObject(0);
			ask = tempJson.getBigDecimal("ask");
			bid = tempJson.getBigDecimal("bid");
			return new AskBidExchangeRates(ask, bid);
		} catch (NullPointerException e) {
			return null;
		} catch(JSONException e) {
			return null;
		}
	}
	
	public AverageExchangeRate createAverageExchangeRate(JSONObject receivedJson) {
		BigDecimal mid;
		try {
			JSONArray gotMid = receivedJson.getJSONArray("rates");
			JSONObject tempJson = gotMid.getJSONObject(0);
			mid = tempJson.getBigDecimal("mid");
			return new AverageExchangeRate(mid);
		} catch (NullPointerException e) {
			return null;
		} catch(JSONException e) {
			return null;
		}
		
	}
}
