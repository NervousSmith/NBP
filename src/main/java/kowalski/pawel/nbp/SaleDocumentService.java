package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleDocumentService {
	
	public void insert() {
		NbpExchangeRatesApi apiForTesting = new NbpExchangeRatesApi();
		BigDecimal result =
				apiForTesting.getExchangeFromCurrencyToPln(CurrencyCodesForTableC.AUD, 
						new BigDecimal("100"), LocalDate.now()).orElse(new BigDecimal("0"));
	}
}
