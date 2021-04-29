package kowalski.pawel.nbp;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import kowalski.pawel.nbp.NbpApi.NbpDataParser;
import kowalski.pawel.nbp.apiInterfaces.DataProvider;

public class TestsForReceiveRateHandler {

	NbpDataParser parser;
	NbpDataProviderMock provider;
	
	
	@Before
	public void load(){
		parser = new NbpDataParser();
		provider = new NbpDataProviderMock();
	}
	
	@Test
	public void shouldReturnRate() {
		//given
		ReceiveRateHandler handler = new ReceiveRateHandler(provider, parser);
		//when
		Optional<BigDecimal> result = 
				handler.receiveData(Currency.GBP, new BigDecimal("23"), LocalDate.of(2012, 1, 31));
		//then
		assertThat(result).isNotEmpty();
	}
	
	@Test
	public void shouldReturnCorrectRate() {
		//given
		ReceiveRateHandler handler = new ReceiveRateHandler(provider, parser);
		//when
		Optional<BigDecimal> result = 
				handler.receiveData(Currency.GBP, new BigDecimal("23"), LocalDate.of(2012, 1, 31));
		//then
		assertThat(result.get()).isEqualByComparingTo("5.0496");
	}
	
	@Test
	public void shouldReturnFridayRateForSaturday() {
		//given
		ReceiveRateHandler handler = new ReceiveRateHandler(provider, parser);
		//when
		Optional<BigDecimal> result = 
				handler.receiveData(Currency.GBP, new BigDecimal("23"), LocalDate.of(2012, 1, 28));
		//then
		assertThat(result.get()).isEqualByComparingTo("5.0551");
	}
	
	@Test
	public void shouldReturnFridayRateForSunday() {
		//given
		ReceiveRateHandler handler = new ReceiveRateHandler(provider, parser);
		//when
		Optional<BigDecimal> result = 
				handler.receiveData(Currency.GBP, new BigDecimal("23"), LocalDate.of(2012, 1, 29));
		//then
		assertThat(result.get()).isEqualByComparingTo("5.0551");
	}
	
	@Test
	public void shouldReturnEmptyOptionalForTooEarlyDate() {
		//given
		ReceiveRateHandler handler = new ReceiveRateHandler(provider, parser);
		//when
		Optional<BigDecimal> result = 
				handler.receiveData(Currency.GBP, new BigDecimal("23"), LocalDate.of(2001, 1, 29));
		//then
		assertThat(result).isEmpty();
	}

}


class NbpDataProviderMock implements DataProvider{

	List<String> jsonList;
	
	@Override
	public String requestData(String currencyCode, LocalDate date) {
		return findByDate(date);
	}
	
	public NbpDataProviderMock(){
		jsonList = new ArrayList<String>();
		jsonList.add("{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"21/A/NBP/2012\",\"effectiveDate\":\"2012-01-31\",\"mid\":5.0496}]}");
		jsonList.add("{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"20/A/NBP/2012\",\"effectiveDate\":\"2012-01-30\",\"mid\":5.0816}]}");
		jsonList.add("{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"19/A/NBP/2012\",\"effectiveDate\":\"2012-01-27\",\"mid\":5.0551}]}");
		jsonList.add("{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"18/A/NBP/2012\",\"effectiveDate\":\"2012-01-26\",\"mid\":5.0588}]}");
		jsonList.add("{\"table\":\"A\",\"currency\":\"funt szterling\",\"code\":\"GBP\",\"rates\":[{\"no\":\"17/A/NBP/2012\",\"effectiveDate\":\"2012-01-25\",\"mid\":5.1385}]}");
	}
	
	private String findByDate(LocalDate date) {
		for (int i = 0; i < jsonList.size(); i++) {
			if(getRateDate(jsonList.get(i)).isEqual(date)) {
				return jsonList.get(i);
			}
		}
		return null;
		
	}
	
	private LocalDate getRateDate(String json) {
		JSONObject tempJson = new JSONObject(json);
		JSONArray gotRate = tempJson.getJSONArray("rates");
		JSONObject tempJsonForDate = gotRate.getJSONObject(0);
		return LocalDate.parse(tempJsonForDate.getString("effectiveDate"));
	}
}