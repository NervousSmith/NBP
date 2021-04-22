package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.interfaces.Currency;

public class SaleDocumentService {
	
	public void insert() {
		ExchangeRatesApi apiForTesting = new ExchangeRatesApi();
		Optional<BigDecimal> result = apiForTesting.calculateExchange(Currency.USD, new BigDecimal("23"), LocalDate.now());
		
	}
}
