package kowalski.pawel.nbp;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.NbpApi.NbpExchangeRatesApi;

public class MainForTests {

	public static void main(String[] args) {

		NbpExchangeRatesApi api = new NbpExchangeRatesApi();
		Optional<BigDecimal> result = api.calculateExchange(Currency.USD, new BigDecimal("23"), LocalDate.now());
		if(result.isPresent()) {
			System.out.println(result.get().toString());
		}else {
			System.out.println("Brak danych");
		}
			
		File justAFile = new File("");
	}

}
