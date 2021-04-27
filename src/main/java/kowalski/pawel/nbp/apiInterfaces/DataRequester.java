package kowalski.pawel.nbp.apiInterfaces;

import java.time.LocalDate;

public interface DataRequester {

	String requestData(String currencyCode, LocalDate date);
	
}
