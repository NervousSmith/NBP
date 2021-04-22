package kowalski.pawel.nbp;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class NbpDataGetter implements DataGetter{

	private Calculator calculator;
	private DataRequester requester;
	private DataReader reader;
	private DataParser parser;
	
	public NbpDataGetter(Calculator calculator, DataRequester requester,
			DataReader reader, DataParser parser) {
		super();
		this.calculator = calculator;
		this.requester = requester;
		this.reader = reader;
		this.parser = parser;
	}
	
	@Override
	public Optional<BigDecimal> receiveData(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		return createOptionalWithResponse(currencyCode, ammount, date);
	}

	private Optional<BigDecimal> createOptionalWithResponse(Currency currencyCode, 
			BigDecimal ammount, LocalDate date) {
		Optional<BigDecimal> result = null;
		try {
		InputStreamReader tempReader = requester.requestData(currencyCode, date);
		String tempString = reader.readReceivedData(tempReader);
		BigDecimal calculationResult = calculator.calculate(ammount, 
				parser.getParsedReadData(tempString));
		result = Optional.ofNullable(calculationResult);
		}catch (NullPointerException e) {
			for (int i = 1; i < 11; i++) {
				result = 
						lookForEffect(currencyCode, ammount, date.minusDays(i));
				if(result.isPresent()) {
					break;
				}
				else if(result.isEmpty()) {
					result = Optional.ofNullable(null);
				}
			}
		}
		return result;
	}
	
		private Optional<BigDecimal> lookForEffect(Currency currencyCode, 
				BigDecimal ammount, LocalDate date){
			try {
				InputStreamReader tempReader = requester.requestData(currencyCode, date);
				String tempString = reader.readReceivedData(tempReader);
				BigDecimal calculationResult = calculator.calculate(ammount, parser.getParsedReadData(tempString));
				return Optional.ofNullable(calculationResult);
			} catch (NullPointerException e) {
				return Optional.ofNullable(null);
			}
		}

}
