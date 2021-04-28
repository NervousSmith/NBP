package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.NbpApi.NbpApi;

public class SaleDocumentService {
	
	public void insert() {
		NbpApi apiForTesting = new NbpApi();
		Optional<BigDecimal> result = 
				apiForTesting.calculateExchange(Currency.USD, new BigDecimal("23"),
						LocalDate.now());
		
	}
}
