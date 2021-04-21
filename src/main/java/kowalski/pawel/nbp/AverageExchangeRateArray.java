package kowalski.pawel.nbp;

import java.util.ArrayList;

import org.json.JSONObject;


public class AverageExchangeRateArray {

	private ArrayList<AverageExchangeRate> averageExchangeRates;

	public AverageExchangeRateArray(JSONObject receivedJson) {
		
	}

	public AverageExchangeRate getAverageRate(int index) {
		return averageExchangeRates.get(index);
	}

	public int getSize() {
		return averageExchangeRates.size();
	}
}
