package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.NbpApi.NbpJsonStrategy;
import kowalski.pawel.nbp.apiInterfaces.Strategy;

public class MainForTests {

	public static void main(String[] args) {
		Strategy jsonNbp = new NbpJsonStrategy();
		ExchangeRateApi nbpApi = new ExchangeRateApi(jsonNbp);
		Optional<BigDecimal> result = 
				nbpApi.calculateExchange(Currency.USD, new BigDecimal("10"), LocalDate.of(2021, 4, 2));
		if (result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("ERROR");
		}
	}
	


}
