package kowalski.pawel.nbp.NbpApi;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import kowalski.pawel.nbp.Currency;
import kowalski.pawel.nbp.interfaces.Calculator;
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

	
	public NbpDataGetter(Calculator calculator, 
			Currency currencyCode, BigDecimal ammount, LocalDate date) {
		super();
		this.calculator = calculator;
		this.requester = new NbpDataRequester(currencyCode.name(), date);
		this.reader = new NbpDataReader();
		this.parser = new NbpDataParser();
		this.currencyCode = currencyCode;
		this.ammount = ammount;
		this.date = date;
	}

	@Override
	public Optional<BigDecimal> receiveData() {
		return createOptionalWithResponse(currencyCode, ammount, date);
	}

	private Optional<BigDecimal> createOptionalWithResponse(Currency currencyCode, BigDecimal ammount, LocalDate date) {
		Optional<BigDecimal> result = null;
		try {
			InputStreamReader tempReader = requester.requestData();
			String tempString = reader.readReceivedData(tempReader);
			BigDecimal calculationResult = calculator.calculate(ammount, parser.getParsedReadData(tempString));
			result = Optional.ofNullable(calculationResult);
		} catch (NullPointerException e) {
			result = lookForEffect(date);
		}
		return result;
	}

	private Optional<BigDecimal> lookForEffect(LocalDate dateToCheck){
		Optional<BigDecimal> result = null;
		for (int i = 1; i < 11; i++) {
			result = checkDate(dateToCheck.minusDays(i));
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
			InputStreamReader tempReader = new NbpDataRequester(currencyCode.name(), dateToCheck).requestData();
			String tempString = reader.readReceivedData(tempReader);
			BigDecimal calculationResult = calculator.calculate(ammount, parser.getParsedReadData(tempString));
			return Optional.ofNullable(calculationResult);
		} catch (NullPointerException e) {
			return Optional.ofNullable(null);
		}
	}

}
