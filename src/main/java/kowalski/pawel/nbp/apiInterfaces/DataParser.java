package kowalski.pawel.nbp.apiInterfaces;

import java.math.BigDecimal;

public interface DataParser {
	
	BigDecimal getParsedReadData(String readData) throws NullPointerException;

}
