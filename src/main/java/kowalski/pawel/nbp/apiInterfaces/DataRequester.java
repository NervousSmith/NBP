package kowalski.pawel.nbp.apiInterfaces;

import java.io.InputStreamReader;

public interface DataRequester {

	InputStreamReader requestData() throws NullPointerException;
	
}
