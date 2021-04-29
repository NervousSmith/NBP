package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.apiInterfaces.Api;
import kowalski.pawel.nbp.apiInterfaces.Strategy;

public class ExchangeRateApi implements Api {

	private ReceiveRateHandler handler;
	private Strategy chosenStrategy; 
	
	public ExchangeRateApi(Strategy strategy){
		this.chosenStrategy = strategy;
	}
	
	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		handler = new ReceiveRateHandler(chosenStrategy.getProvider(), 
				chosenStrategy.getParser());
		return handler.receiveData(currencyCode, ammount, date);
	}

	@Override
	public Optional<BigDecimal> calculateExchange(Currency currencyCode, 
			BigDecimal ammount) {
		handler = new ReceiveRateHandler(chosenStrategy.getProvider(),
				chosenStrategy.getParser());
		return handler.receiveData(currencyCode, ammount, LocalDate.now());
	}
}