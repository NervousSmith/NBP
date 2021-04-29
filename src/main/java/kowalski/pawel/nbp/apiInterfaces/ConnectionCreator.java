package kowalski.pawel.nbp.apiInterfaces;

import java.io.InputStream;
import java.net.URL;

public interface ConnectionCreator {

	InputStream createConnection(URL url);
	
}
