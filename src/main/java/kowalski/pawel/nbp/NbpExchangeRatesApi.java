package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

public class NbpExchangeRatesApi {

	private RequestResponseHandler currencyHandler;
	private final String tableC = "c";
	private final String tableA = "a";
	private RatesCreator creator = new RatesCreator();

	public BigDecimal getExchangeFromPlnToCurrency(CurrencyCodesForTableC currencyCode, BigDecimal pln,
			LocalDate date) {
		currencyHandler = new RequestResponseHandler();
		AskBidExchangeRates createdAskBidExchangeRates = creator
				.createAskBidExchangeRates(currencyHandler.getJson(tableC, currencyCode.name(), date));
		return pln.divide(createdAskBidExchangeRates.getBid(), 6, RoundingMode.UP);
	}

	public Optional<BigDecimal> getExchangeFromCurrencyToPln(CurrencyCodesForTableC currencyCode, BigDecimal currency,
			LocalDate date) {
		currencyHandler = new RequestResponseHandler();
		AskBidExchangeRates createdAskBidExchangeRates = 
				creator.createAskBidExchangeRates(currencyHandler.getJson(tableC, currencyCode.name(), date));
		Optional<AskBidExchangeRates> createdAskBidOptional = Optional.ofNullable(createdAskBidExchangeRates);
		if(createdAskBidOptional.isPresent()) {
			return Optional.ofNullable(currency.multiply(createdAskBidExchangeRates.getAsk()));
		}
		else {
			return Optional.ofNullable(null);
		}
	}
	
	public Optional<BigDecimal> getExchangeFromCurrencyToPlnByAverage(CurrencyCodesForTableA currencyCode, 
			BigDecimal currency, LocalDate date) {
		currencyHandler = new RequestResponseHandler();
		AverageExchangeRate createdAverageExchangeRate = creator
				.createAverageExchangeRate(currencyHandler.getJson(tableA, currencyCode.name(), date));
		return Optional.ofNullable(currency.multiply(createdAverageExchangeRate.getMid()));
	}
	
	public Optional<BigDecimal> getExchangeFromPlnToCurrencyByAverage(CurrencyCodesForTableA currencyCode, 
			BigDecimal currency,
			LocalDate date) {
		currencyHandler = new RequestResponseHandler();
		AverageExchangeRate createdAskBidExchangeRates = creator
				.createAverageExchangeRate(currencyHandler.getJson(tableA, currencyCode.name(), date));
		return Optional.ofNullable(currency.multiply(createdAskBidExchangeRates.getMid()));
	}
}