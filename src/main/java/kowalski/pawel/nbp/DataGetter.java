package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface DataGetter {

	Optional<BigDecimal> receiveData(Currency currencyCode, BigDecimal ammount, LocalDate date);
	
}
