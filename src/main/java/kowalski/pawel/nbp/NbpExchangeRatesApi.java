package kowalski.pawel.nbp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

public class NbpExchangeRatesApi {
	
	private DataParser parser;
	private DataReader reader;
	private DataRequester requester;
	Calculator calculator = new Calculator();
	

	public BigDecimal calculateExchangeRateForADateFromNbp(Currency currencyCode, BigDecimal ammount, LocalDate date) {
		requester = new NbpDataRequester(currencyCode.name(), date);
		reader = new NbpDataReader(requester.requestData());
		parser = new NbpReadDataParser(reader.readReceivedData());
		return calculator.multiply(ammount, parser.getParsedReadData());
	}
}