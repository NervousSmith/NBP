package kowalski.pawel.nbp.interfaces;

import java.math.BigDecimal;
import java.util.Optional;

public interface DataGetter {

	Optional<BigDecimal> receiveData();
	
}
