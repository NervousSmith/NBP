package kowalski.pawel.nbp.apiInterfaces;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;

public interface Api {
	
	Optional<BigDecimal> calculateExchange(Currency currencyCode,
			BigDecimal ammount, LocalDate date);
	
	Optional<BigDecimal> calculateExchange(Currency currencyCode,
			BigDecimal ammount);
	
}
