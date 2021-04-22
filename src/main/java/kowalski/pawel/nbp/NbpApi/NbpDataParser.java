package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kowalski.pawel.nbp.interfaces.DataParser;

public class NbpDataParser implements DataParser{

	private String receivedData;

	@Override
	public BigDecimal getParsedReadData(String receivedData) {
		this.receivedData = receivedData;
		return readRate();
	}
	
	private JSONObject createJsonFromReceivedString() {
		try {
		return new JSONObject(receivedData);
		} catch (JSONException e) {
			return null;
		}
	}

	private BigDecimal readRate() {
		try {
			JSONArray gotRate = createJsonFromReceivedString().getJSONArray("rates");
			JSONObject tempJson = gotRate.getJSONObject(0);
			return tempJson.getBigDecimal("mid");
		} catch(JSONException e) {
			return null;
		}
	}
}