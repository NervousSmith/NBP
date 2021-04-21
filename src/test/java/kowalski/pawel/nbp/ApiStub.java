package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class ApiStub {

	@SuppressWarnings("null")
	public Optional<BigDecimal> getExchangeFromCurrencyToPln(CurrencyCodesForTableC currencyCode, BigDecimal currency,
			LocalDate date) {
		AskBidExchangeRates createdAskBidExchangeRates = null;
		Optional<AskBidExchangeRates> createdAskBidOptional = Optional.ofNullable(createdAskBidExchangeRates);
		if(createdAskBidOptional.isPresent()) {
			return Optional.ofNullable(currency.multiply(createdAskBidExchangeRates.getAsk()));
		}
		else {
			return Optional.ofNullable(null);
		}
	}
}
