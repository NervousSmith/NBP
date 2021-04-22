package kowalski.pawel.nbp;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.interfaces.Currency;

public class MainForTests {

	public static void main(String[] args) {

		ExchangeRatesApi api = new ExchangeRatesApi();
		Optional<BigDecimal> result = api.calculateExchange(Currency.USD, new BigDecimal("23"), LocalDate.of(2001, 1 ,2));
		if(result.isPresent()) {
			System.out.println(result.get().toString());
		}else {
			System.out.println("Brak danych");
		}
			
		File justAFile = new File("");
	}

}
