package kowalski.pawel.nbp;

import java.util.ArrayList;

import org.json.JSONObject;

public class AskBidExchangeRatesArray {

	private ArrayList<AskBidExchangeRates> bothExchangeRates;

	public AskBidExchangeRatesArray(JSONObject receivedJson) {

	}

	public AskBidExchangeRates getAskBidExchangeRate(int index) {
		return bothExchangeRates.get(index);
	}

	public int getSize() {
		return bothExchangeRates.size();
	}
}
