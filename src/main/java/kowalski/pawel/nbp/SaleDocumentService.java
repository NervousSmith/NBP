package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.NbpApi.NbpExchangeRatesApi;

public class SaleDocumentService {
	
	public void insert() {
		NbpExchangeRatesApi apiForTesting = new NbpExchangeRatesApi();
		Optional<BigDecimal> result = apiForTesting.calculateExchange(Currency.USD, new BigDecimal("23"), LocalDate.now());
		
	}
}
