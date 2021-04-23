package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.Multiplyer;
import kowalski.pawel.nbp.interfaces.Api;
import kowalski.pawel.nbp.interfaces.Calculator;
import kowalski.pawel.nbp.interfaces.DataGetter;

public class NbpExchangeRatesApi implements Api{
	
	private Calculator calculator;
	private DataGetter getter;

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode,
			BigDecimal ammount, LocalDate date) {
		date = verifyDate(date);
		calculator = new Multiplyer();
		getter = new NbpDataGetter(calculator, currencyCode, ammount, date);
		return getter.receiveData();
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, BigDecimal ammount) {
		calculator = new Multiplyer();
		getter = new NbpDataGetter(calculator, currencyCode, ammount, LocalDate.now());
		return getter.receiveData();
	}

	private LocalDate verifyDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			return LocalDate.now();
		} else {
			return date;
		}
		
	}
}