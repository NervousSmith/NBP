package kowalski.pawel.nbp.NbpApi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.ReceiveRateHandler;
import kowalski.pawel.nbp.apiInterfaces.Api;

public class NbpApi implements Api {

	private NbpDataParser parser;
	private NbpDataProvider provider;
	private ReceiveRateHandler handler;
	
	
	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		provider = new NbpDataProvider();
		parser = new NbpDataParser();
		handler = new ReceiveRateHandler(provider, parser);
		return handler.receiveData(currencyCode, ammount, date);
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount) {
		provider = new NbpDataProvider();
		parser = new NbpDataParser();
		handler = new ReceiveRateHandler(provider, parser);
		return handler.receiveData(currencyCode, ammount, LocalDate.now());
	}
}