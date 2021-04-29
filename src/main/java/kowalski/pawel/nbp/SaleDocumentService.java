package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class SaleDocumentService {
	
	public void insert() {
		ExchangeRateApi apiForTesting = new ExchangeRateApi();
		Optional<BigDecimal> result = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("23"),
						LocalDate.now());
		
	}
}
