package kowalski.pawel.nbp;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import kowalski.pawel.nbp.NbpApi.NbpExchangeRatesApi;

public class TestsForGettingCurrencyExchange {

	NbpExchangeRatesApi apiForTesting;
	
	@Before
	public void loadApi() {
		apiForTesting = new NbpExchangeRatesApi();
	}
	
	@Test
	public void shouldReturnFridayRateForSaturday() {
		//given
		Optional<BigDecimal> fridayRate =
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2021, 4, 16));
		//when
		Optional<BigDecimal> staurdayRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2021, 4, 17));
		//then
		assertThat(staurdayRate.get().toString())
						.isEqualTo(fridayRate.get().toString());
	}
	
	@Test
	public void shouldReturnRateFromFridayForSunday() {
		//given
		Optional<BigDecimal> fridayRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2021, 4, 16));
		//when
		Optional<BigDecimal> staurdayRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2021, 4, 18));
		//then
		assertThat(staurdayRate.get().toString())
						.isEqualTo(fridayRate.get().toString());
	}
	
	@Test
	public void shouldReturnRateFromFridayBeforEaster() {
		//given
		Optional<BigDecimal> fridayBeforeEasterRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2021, 4, 2));
		//when
		Optional<BigDecimal> mondayEasterRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2021, 4, 5));
		//then
		assertThat(mondayEasterRate.get().toString())
						.isEqualTo(fridayBeforeEasterRate.get().toString());
	}
	
	@Test 
	public void shouldReturnLatestRateForFutureDate() {
		//given
		Optional<BigDecimal> todayRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.now());
		//when
		Optional<BigDecimal> futureDateRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.now().plusDays(3));
		//then
		assertThat(futureDateRate.get().toString())
						.isEqualTo(todayRate.get().toString());
	}
	
	@Test
	public void shouldReturnNullForTooEarlyDate() {
		//given
		
		//when
		Optional<BigDecimal> futureDateRate = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("32"), LocalDate.of(2001, 2, 2));
		//then
		assertThat(futureDateRate)
						.isEmpty();
	}
	
	
}
