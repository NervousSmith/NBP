package kowalski.pawel.nbp;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.FileApi.FileXmlStrategy;
import kowalski.pawel.nbp.apiInterfaces.Strategy;

public class MainForTests {

	public static void main(String[] args) {
		Strategy xmlFile = new FileXmlStrategy(new File(""));
		ExchangeRateApi nbpApi = new ExchangeRateApi(xmlFile);
		Optional<BigDecimal> result = 
				nbpApi.calculateExchange(Currency.USD, new BigDecimal("10"), LocalDate.of(2021, 4, 2));
		if (result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("ERROR");
		}
	}
	


}
