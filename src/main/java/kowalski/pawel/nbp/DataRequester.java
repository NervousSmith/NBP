package kowalski.pawel.nbp;

import java.io.InputStreamReader;
import java.time.LocalDate;

public interface DataRequester {

	InputStreamReader requestData(Currency currencyCode, LocalDate date);
	
}
