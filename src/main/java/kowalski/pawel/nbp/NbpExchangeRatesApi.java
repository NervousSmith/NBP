package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class NbpExchangeRatesApi implements Api{
	
	private DataParser parser;
	private DataReader reader;
	private DataRequester requester;
	private Calculator calculator;
	private DataGetter getter;

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode,
			BigDecimal ammount, LocalDate date) {
		date = verifyDate(date);
		parser = new NbpDataParser();
		reader = new NbpDataReader();
		requester = new NbpDataRequester();
		calculator = new Multiplyer();
		getter = new NbpDataGetter(calculator, requester, reader, parser);
		return getter.receiveData(currencyCode, ammount, date);
	}


	private LocalDate verifyDate(LocalDate date) {
		if (date.isAfter(LocalDate.now())) {
			return LocalDate.now();
		} else {
			return date;
		}
		
	}
}