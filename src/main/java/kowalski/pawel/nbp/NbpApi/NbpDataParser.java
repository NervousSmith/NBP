package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kowalski.pawel.nbp.apiInterfaces.DataParser;

public class NbpDataParser implements DataParser {

	private String receivedData;

	@Override
	public BigDecimal parseReadData(String receivedData){
		this.receivedData = receivedData;
		return readRate();
	}

	private JSONObject createJsonFromReceivedString() {
		try {
			return new JSONObject(receivedData);
		} catch (JSONException e) {
			return new JSONObject();
		}
	}

	private BigDecimal readRate() {
		JSONArray gotRate = createJsonFromReceivedString().getJSONArray("rates");
		JSONObject tempJson = gotRate.getJSONObject(0);
		return tempJson.getBigDecimal("mid");
	}
}