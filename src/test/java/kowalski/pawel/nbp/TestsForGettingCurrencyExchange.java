package kowalski.pawel.nbp;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class TestsForGettingCurrencyExchange {

	NbpExchangeRatesApi apiForTesting;
	
	@Before
	public void loadApi() {
		apiForTesting = new NbpExchangeRatesApi();
	}
	
	
	@Test
	public void shouldReturnRateFromFridayForSunday() {
		//Given
		Optional<BigDecimal> rateToCheck = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.of(2021, 4, 18));
		Optional<BigDecimal> rightRate = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.of(2021, 4, 16));
		BigDecimal fridayRate = rightRate.orElse(new BigDecimal("0"));
		//When
		BigDecimal sundayRate = rateToCheck.orElse(new BigDecimal("1"));
		//Then
		assertThat(sundayRate)
			.isEqualTo(fridayRate);
	}
	
	@Test
	public void shouldReturnRateFromOneDaysEarlier() {
		//Given
		Optional<BigDecimal> rateToCheck = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.of(2021, 4, 17));
		Optional<BigDecimal> rightRate = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.of(2021, 4, 16));
		BigDecimal fridayRate = rightRate.orElse(new BigDecimal("0"));
		//When
		BigDecimal sundayRate = rateToCheck.orElse(new BigDecimal("1"));
		//Then
		assertThat(sundayRate)
			.isEqualTo(fridayRate);
	}
	
	@Test
	public void shouldReturnLatestWithFutureDate() {
		//Given
		Optional<BigDecimal> rateToCheck = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.now().plusDays(25));
		Optional<BigDecimal> rightRate = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.now());
		BigDecimal fridayRate = rightRate.orElse(new BigDecimal("0"));
		//When
		BigDecimal sundayRate = rateToCheck.orElse(new BigDecimal("1"));
		//Then
		assertThat(sundayRate)
			.isEqualTo(fridayRate);
	}
	
	@Test
	public void shouldReturnRateFromBeforeEaster() {
		//Given
		Optional<BigDecimal> rateToCheck = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.of(2021, 4, 5));
		Optional<BigDecimal> rightRate = 
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.USD, 
						new BigDecimal("1"), LocalDate.of(2021, 4, 2));
		BigDecimal fridayRate = rightRate.orElse(new BigDecimal("0"));
		//When
		BigDecimal sundayRate = rateToCheck.orElse(new BigDecimal("1"));
		//Then
		assertThat(sundayRate)
			.isEqualTo(fridayRate);
	}
	
}
