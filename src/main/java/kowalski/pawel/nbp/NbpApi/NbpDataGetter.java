package kowalski.pawel.nbp.NbpApi;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.interfaces.Calculator;
import kowalski.pawel.nbp.interfaces.Currency;
import kowalski.pawel.nbp.interfaces.DataGetter;
import kowalski.pawel.nbp.interfaces.DataParser;
import kowalski.pawel.nbp.interfaces.DataReader;
import kowalski.pawel.nbp.interfaces.DataRequester;

public class NbpDataGetter implements DataGetter {

	private Calculator calculator;
	private DataRequester requester;
	private DataReader reader;
	private DataParser parser;
	private Currency currencyCode;
	private BigDecimal ammount;
	private LocalDate date;

	public NbpDataGetter(Calculator calculator, DataRequester requester, DataReader reader, DataParser parser) {
		super();
		this.calculator = calculator;
		this.requester = requester;
		this.reader = reader;
		this.parser = parser;
	}

	@Override
	public Optional<BigDecimal> receiveData(Currency currencyCode, BigDecimal ammount, LocalDate date) {
		this.date = date;
		this.ammount = ammount;
		this.currencyCode = currencyCode;
		return createOptionalWithResponse(currencyCode, ammount, date);
	}

	private Optional<BigDecimal> createOptionalWithResponse(Currency currencyCode, BigDecimal ammount, LocalDate date) {
		Optional<BigDecimal> result = null;
		try {
			InputStreamReader tempReader = requester.requestData(currencyCode, date);
			String tempString = reader.readReceivedData(tempReader);
			BigDecimal calculationResult = calculator.calculate(ammount, parser.getParsedReadData(tempString));
			result = Optional.ofNullable(calculationResult);
		} catch (NullPointerException e) {
			result = lookForEffect();
		}
		return result;
	}

	private Optional<BigDecimal> lookForEffect(){
		Optional<BigDecimal> result = null;
		for (int i = 1; i < 11; i++) {
			result = checkDate(date.minusDays(i));
			if (result.isPresent()) {
				break;
			} else if (result.isEmpty()) {
				result = Optional.ofNullable(null);
			}
		}
		return result;
	}

	private Optional<BigDecimal> checkDate(LocalDate dateToCheck) {
		try {
			InputStreamReader tempReader = requester.requestData(currencyCode, dateToCheck);
			String tempString = reader.readReceivedData(tempReader);
			BigDecimal calculationResult = calculator.calculate(ammount, parser.getParsedReadData(tempString));
			return Optional.ofNullable(calculationResult);
		} catch (NullPointerException e) {
			return Optional.ofNullable(null);
		}
	}

}
