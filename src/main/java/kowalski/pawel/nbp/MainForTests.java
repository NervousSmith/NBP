package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class MainForTests {

	public static void main(String[] args) {
		NbpExchangeRatesApi api = new NbpExchangeRatesApi();
		Optional<BigDecimal> result = api.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.EUR, 
				new BigDecimal("123"), LocalDate.of(2021, 4, 16));

		BigDecimal resultBigDecimal = result.orElse(new BigDecimal("0"));
		System.out.println(resultBigDecimal);
		}
	
	
}
