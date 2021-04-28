package kowalski.pawel.nbp.apiInterfaces;

import java.time.LocalDate;

public interface DataProvider {

	String requestData(String currencyCode, LocalDate date);
	
}
