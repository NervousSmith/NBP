package kowalski.pawel.nbp.apiInterfaces;

import java.math.BigDecimal;
import java.util.Optional;

public interface DataGetter {

	Optional<BigDecimal> receiveData();
	
}
