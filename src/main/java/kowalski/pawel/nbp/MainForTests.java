package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainForTests {

	public static void main(String[] args) {

		NbpExchangeRatesApi api = new NbpExchangeRatesApi();
		
		System.out.println(api.calculateExchangeRateForADateFromNbp(Currency.USD, new BigDecimal("24"), LocalDate.now()));
		}
	
	
	
	
}
