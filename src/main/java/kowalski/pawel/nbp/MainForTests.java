package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class MainForTests {

	public static void main(String[] args) {

		
		Api api = new NbpExchangeRatesApi();
		Optional<BigDecimal> testingOptional = api.calculateExchange(Currency.USD,
				new BigDecimal("24"), LocalDate.of(2021, 4, 5));
		Optional<BigDecimal> testingOptional2 = api.calculateExchange(Currency.USD,
				new BigDecimal("24"), LocalDate.of(2021, 4, 2));
		System.out.println(testingOptional);
		System.out.println(testingOptional2);
	}
	
	
	
	
}
