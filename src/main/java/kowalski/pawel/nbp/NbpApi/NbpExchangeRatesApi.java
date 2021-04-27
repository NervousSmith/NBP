package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.apiInterfaces.Api;

public class NbpExchangeRatesApi implements Api {

	NbpDataGetter getter;

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		getter = new NbpDataGetter();
		return getter.receiveData(currencyCode, ammount, date);
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount) {
		getter = new NbpDataGetter();
		return getter.receiveData(currencyCode, ammount, LocalDate.now());
	}
}