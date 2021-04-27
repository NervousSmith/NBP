package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.apiInterfaces.Api;

public class NbpExchangeRatesApi implements Api {

	NbpDataGetter getter = new NbpDataGetter(null, null, null);

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		getter = new NbpDataGetter(currencyCode, ammount, date);
		return getter.receiveData();
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount) {
		getter = new NbpDataGetter(currencyCode, ammount, LocalDate.now());
		return getter.receiveData();
	}
}