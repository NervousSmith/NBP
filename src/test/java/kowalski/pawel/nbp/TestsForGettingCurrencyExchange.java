package kowalski.pawel.nbp;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class TestsForGettingCurrencyExchange {

	Api apiForTesting;
	
	@Before
	public void loadApi() {
		apiForTesting = new NbpExchangeRatesApi();
	}

	
	@Test
	public void shouldReturnLatestRateFromNbp() {
		
	}
	
	@Test
	public void shouldReturnFridayRateForSaurday() {
		
	}
	
	@Test
	public void shouldReturnRateFromFridayForSunday() {
		
	}
	
	@Test
	public void shouldReturnRateFromFridayBeforEaster() {
		
	}
	
	@Test 
	public void shouldReturnLatestRateForFutureDate() {
		
	}
	
	@Test
	public void shouldReturnNullForTooEarlyDate() {
		
	}
	
	
}
